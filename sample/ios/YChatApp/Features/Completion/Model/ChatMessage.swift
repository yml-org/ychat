//
//  ChatMessage.swift
//  ios-sample
//
//  Created by Koji Osugi on 21/01/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

struct ChatMessage: Identifiable, Equatable {
    let id: String
    var message: String = ""
    var type: MessageType = .human(error: false)
    var url: String?

    enum MessageType: Equatable {
        case human(error: Bool), bot, loading
    }
}
