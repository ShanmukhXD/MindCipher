import java.util.*;
class MindCipher{
    static Scanner sc = new Scanner(System.in);
    public static void main(){
        getDifficulty();
    }
    static void getDifficulty(){
        System.out.println("---MENU--- \n1. Easy\n2. Medium\n3. Hard");
        int choice = sc.nextInt();
        switch(choice){
            case 1:
                generate(3);
                break;
            case 2:
                generate(4);
                break;
            case 3:
                generate(5);
                break;
            default:
                System.out.println("Invalid choice");
        }
    }
    static void generate(int codeLength){
        final char sampleSpace[] = {'B', 'Y', 'O', 'P', 'W', 'G', 'R'};
        char secret[] = new char[codeLength];
        for(int i=0;i<codeLength;i++)
            secret[i] = sampleSpace[(int)(Math.random()*sampleSpace.length)];
        System.out.println("Enter number of tries you want: ");
        int maxTries = sc.nextInt();
        game(maxTries, secret);
    }
    static void game(int maxTries, char secret[]){
        sc.nextLine();
        int attempt = 1;
        while(maxTries>0){
            System.out.println("ATTEMPT "+attempt+"\nEnter your guess: ");
            String guessInput = sc.nextLine().toUpperCase();
            char guess[] = guessInput.toCharArray();
            if(guess.length != secret.length){
                System.out.println("Invalid guess length- Expected length: "+secret.length);
                continue;
            }
            int correctPosition = 0;
            int correctLetter = 0;
            boolean usedSecret[] = new boolean[secret.length];
            boolean usedGuess[] = new boolean[secret.length];

            for(int i = 0; i<secret.length; i++){
                if(guess[i] == secret[i]){
                    correctPosition++;
                    usedSecret[i] = true;
                    usedGuess[i] = true;
                }
            }
            for(int i = 0; i<secret.length; i++){
                if(usedGuess[i])
                    continue;
                for(int j = 0; j<secret.length; j++){
                    if(!usedSecret[j] && guess[i] == secret[j]){
                        correctLetter++;
                        usedSecret[j] = true;
                        break;
                    }
                }
            }

            System.out.println("Correct position: "+correctPosition);
            System.out.println("Correct letter(wrong position): "+correctLetter);

            if(correctPosition == secret.length){
                System.out.println("You guessed correctly!");
                return;
            }
            maxTries--;
            attempt++;
        }
        System.out.println("Game Over! Secret was: ");
        for(int i = 0; i<secret.length; i++)
            System.out.print(secret[i]+" ");
    }
}