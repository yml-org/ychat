package co.yml.ychat.jvm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import co.yml.ychat.jvm.services.YChatService;

@RestController
@RequestMapping("api/ychat")
public class YChatController {

    @Autowired
    private YChatService YChatService;

    @GetMapping("completion")
    public ResponseEntity<String> completion(
            @RequestParam(value = "input", defaultValue = Defaults.COMPLETION_INPUT) String input
    ) throws Exception {
        String result = YChatService.getCompletionAnswer(input);
        return ResponseEntity.ok(result);
    }

    @GetMapping("chat-completions")
    public ResponseEntity<String> chatCompletions(
            @RequestParam(value = "input", defaultValue = Defaults.CHAT_COMPLETION_INPUT) String input,
            @RequestParam(value = "topic", defaultValue = Defaults.CHAT_COMPLETION_TOPIC) String topic
    ) throws Exception {
        String result = YChatService.getChatCompletionsAnswer(input, topic);
        return ResponseEntity.ok(result);
    }

    @GetMapping("image-generations")
    public ResponseEntity<String> imageGenerations(
            @RequestParam(value = "prompt", defaultValue = Defaults.CHAT_COMPLETION_INPUT) String input
    ) throws Exception {
        String result = YChatService.getImageGenerationsAnswer(input);
        return ResponseEntity.ok(result);
    }

    private static class Defaults {
        static final String COMPLETION_INPUT = "Say this is a test.";
        static final String CHAT_COMPLETION_INPUT = "Tell me one strength exercise";
        static final String CHAT_COMPLETION_TOPIC = "fitness";
    }
}
