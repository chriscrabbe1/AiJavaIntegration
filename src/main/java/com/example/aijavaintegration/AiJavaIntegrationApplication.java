package com.example.aijavaintegration;

import java.util.Map;
import org.springframework.ai.client.AiClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping
public class AiJavaIntegrationApplication {
  private final AiClient mrBot;

  AiJavaIntegrationApplication(AiClient mrBot) {
    this.mrBot = mrBot;
  }
  @GetMapping("/myQuery")
  Map<String, String> query () {

    var prompt = """
              
          Hi, I have here a Greyhound Race with the following start time, event name, and runners:

         {
           "marketId": 229668452,
           "runnerIds": {
             "1. Endova Mccoy": 56616907,
             "4. Razldazl Randall": 65539557,
             "3. Cahir Bridge": 47063784,
             "5. Glengar Scholar": 57090505,
             "2. Swift Barney": 50727225
           },
           "marketName": "H2 380m",
           "eventName": "Crayford 6th Jun",
           "marketTime": "2024-06-06T14:19:00.000+00:00"
         }

              I would like you to imagine that you are an expert Greyhound racing tipster, the best in the world!
              I would like you to make a prediction of which runner will win this race, in your expert opinion. Don't worry about the final result, I just want to hear your opinion on the matter

              To form your prediction you could analyse any past performances of these runners, as well as their current form. You can also analyse the sentiment from current tipsters on the following websites, as well as any other sources that aren't listed here:

              the racing post
              myracing
              timeform
              betfair
              twitter
              OLBG
              sporting life

              Using a combination of expert tipsters' sentiment, and your own analysis please tell me who you think the overrall winner will be, and give me a win probability for each of the runners, outputting the data in the following format:

              "marketModel": {
                  "marketId": <marketId>,
                   "selectionPredictions": [
                      {
                          "selection": {
                              "id": <id>,
                              "handicap": 0.0
                          },
                          "probability": <your calculation>
                      }
                  ]
              }

              Thank you and good luck!
        """;

    var response = this.mrBot.generate(prompt);
    return Map.of("query", response.replace("\n", ""));
  }

  public static void main(String[] args) {
    SpringApplication.run(AiJavaIntegrationApplication.class, args);
  }

}
