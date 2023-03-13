import SwiftUI

struct CompletionView: View {
    @ObservedObject
    private var viewModel: CompletionViewModel

    private var enableButton: Bool {
        !viewModel.isLoading && !viewModel.message.isEmpty
    }

    init(viewModel: CompletionViewModel = .init()) {
        self.viewModel = viewModel
    }

    var body: some View {
        VStack {
            if viewModel.chatMessageList.isEmpty {
                emptyMessage()
                    .padding(.top, 16)
            } else {
                ScrollViewReader { value in
                    ScrollView {
                        ForEach(viewModel.chatMessageList) {
                            chatBubble(chatMessage: $0)
                                .padding(.top, 16)
                                .id($0.id)
                        }
                        .padding(.horizontal, 8)
                    }
                    .onChange(of: viewModel.chatMessageList) {
                        value.scrollTo($0.last?.id, anchor: .bottom)
                    }
                }
            }
            Spacer()
            sendMessageSection()
                .padding(.horizontal, 12)
                .padding(.vertical, 12)
        }.fullScreen()
    }
}

private extension CompletionView {
    @ViewBuilder
    private func emptyMessage() -> some View {
        ZStack {
            Text("Enter any message and chat GPT will answer it.")
                .foregroundColor(.grayMain)
                .style(.caption)
                .bold()
        }
        .padding(.horizontal, 16)
        .padding(.vertical, 8)
        .background(Color.grayLight)
        .cornerRadius(16)
        .padding(.horizontal, 32)
    }

    @ViewBuilder
    private func chatBubble(chatMessage: ChatMessage) -> some View {
        switch chatMessage.type {
        case .human(let error):
            HStack(spacing: 4) {
                Spacer()
                Spacer().frame(width: 60)
                humanChatBubble(message: chatMessage.message)
                if error {
                    Image(uiImage: Icon.warningOutline.uiImage)
                        .renderingMode(.template)
                        .foregroundColor(.red)
                }
            }
        case .bot:
            HStack {
                if let imageUrl = chatMessage.url {
                    botImageBubble(imageUrl)
                    Spacer()
                } else {
                    botChatBubble(message: chatMessage.message)
                    Spacer().frame(width: 60)
                    Spacer()
                }
            }
        case .loading:
            HStack {
                TypingLoading()
                Spacer()
            }
        }
    }

    @ViewBuilder
    private func humanChatBubble(message: String) -> some View {
        ZStack {
            Text(message)
                .foregroundColor(.white)
                .style(.body)
        }
        .padding(.horizontal, 16)
        .padding(.vertical, 8)
        .background(Color.accentMain)
        .cornerRadius(16, corners: [.bottomLeft, .topLeft, .topRight])
    }

    @ViewBuilder
    private func botChatBubble(message: String) -> some View {
        HStack(alignment: .top, spacing: 4) {
            Circle()
                .fill(.green)
                .frame(width: 40, height: 40)
                .overlay {
                    Image(uiImage: Icon.bot.uiImage)
                        .renderingMode(.template)
                        .foregroundColor(.white)
                }
            ZStack {
                Text(message)
                    .foregroundColor(.grayDark)
                    .style(.body)
                    .multilineTextAlignment(.leading)
            }
            .padding(.horizontal, 16)
            .padding(.vertical, 8)
            .background(Color.grayLight)
            .cornerRadius(16, corners: [.bottomLeft, .bottomLeft, .topRight])
        }
    }
    
    @ViewBuilder
    private func botImageBubble(_ url: String) -> some View {
        HStack(alignment: .top, spacing: 4) {
            Circle()
                .fill(.green)
                .frame(width: 40, height: 40)
                .overlay {
                    Image(uiImage: Icon.bot.uiImage)
                        .renderingMode(.template)
                        .foregroundColor(.white)
                }
            ZStack {
                AsyncImage(url: URL(string: url))
                    .foregroundColor(.grayDark)
            }
            .padding(.horizontal, 16)
            .padding(.vertical, 8)
            .background(Color.grayLight)
            .cornerRadius(16, corners: [.bottomLeft, .bottomLeft, .topRight])
        }
    }

    @ViewBuilder
    private func sendMessageSection() -> some View {
        HStack(spacing: 8) {
            TextField(text: $viewModel.message) {
                Text("Message")
                    .foregroundColor(.grayMedium)
                    .style(.subtitle)
            }
            .textFieldStyle(DefaultTextFieldStyle())
            .disabled(viewModel.isLoading)
            .opacity(viewModel.isLoading ? 0.4 : 1)
            sendButton()
        }
    }

    @ViewBuilder
    private func sendButton() -> some View {
        Button { viewModel.sendMessage() } label: {
            Circle()
                .fill(enableButton ? Color.accentMain : .grayLight)
                .frame(width: 40, height: 40)
                .overlay {
                    Image(uiImage: Icon.send.uiImage)
                        .renderingMode(.template)
                        .foregroundColor(.white)
                }
        }
        .disabled(viewModel.message.isEmpty)
    }
}

private struct TypingLoading: View {
    @State
    private var typingState = "Typing."

    @State
    private var timer: Timer?

    var body: some View {
        ZStack {
            Text(typingState)
                .foregroundColor(.grayMedium)
                .style(.body)
        }
        .padding(.horizontal, 16)
        .padding(.vertical, 8)
        .background(Color.grayLight)
        .cornerRadius(16, corners: [.bottomLeft, .bottomLeft, .topRight])
        .onAppear {
            timer = Timer.scheduledTimer(withTimeInterval: 0.25, repeats: true) { _ in
                switch typingState {
                case "Typing...": typingState = "Typing."
                case "Typing.": typingState = "Typing.."
                default: typingState = "Typing..."
                }
            }
        }
        .onDisappear {
            timer?.invalidate()
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        NavigationStack {
            CompletionView()
                .applyToolbar("Completion")
        }
    }
}
