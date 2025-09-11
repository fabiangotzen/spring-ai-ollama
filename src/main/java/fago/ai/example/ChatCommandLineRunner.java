package fago.ai.example;

import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ChatCommandLineRunner implements CommandLineRunner {

    private final OllamaChatModel chatModel;

    public ChatCommandLineRunner(OllamaChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== Chat Terminal ===");
        System.out.println("Type your messages below (type 'exit' or 'quit' to stop):");
        System.out.println("=========================================\n");

        while (true) {
            System.out.print("You: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit") ||
                    input.equalsIgnoreCase("quit") ||
                    input.equalsIgnoreCase("q")) {
                System.out.println("Goodbye!");
                break;
            }

            if (input.isEmpty()) {
                continue;
            }

            try {
                System.out.print("AI: ");
                String response = chatModel.call(input);
                System.out.println(response);
                System.out.println();

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println();
            }
        }
        scanner.close();
        System.exit(0);
    }
}