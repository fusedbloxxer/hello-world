package probleme.stive;

import exceptions.PopException;
import structures.GenericList;
import structures.Stack;
import utils.HelperSingleton;

import java.io.FileNotFoundException;

public class ProblemFive {

    public static final String POSTFIX_TXT = "postfix.txt";

    public static void main(String[] args) throws FileNotFoundException {
        GenericList<String> list = HelperSingleton.getInstance().readLines(POSTFIX_TXT);

        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " -> ");
            Stack stack = new Stack();

            try {
                for (String string : list.get(i).split("\\s")) {
                    if (!isNumber(string)) {
                        switch (string.charAt(0)) {
                            case '+':
                                stack.push(stack.pop() + stack.pop());
                                break;
                            case '-':
                                stack.push(-stack.pop() + stack.pop());
                                break;
                            case '*':
                                stack.push(stack.pop() * stack.pop());
                                break;
                            case '/':
                                stack.push((int) ((1 / (double) stack.pop()) * stack.pop()));
                                break;
                            case '%': {
                                int b = stack.pop();
                                int a = stack.pop();
                                stack.push(a % b);
                            }
                            break;
                            default:
                                throw new Exception("Invalid input.");
                        }
                    } else {
                        stack.push(Integer.parseInt(string));
                    }
                }

                int lastElement = stack.pop();
                if (!stack.empty()) {
                    System.out.println("Insufficient elements.");
                } else {
                    System.out.println(lastElement);
                }

            } catch (PopException ex) {
                System.out.println("Cannot pop more elements.");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static boolean isNumber(String string) {
        return string.matches("^-?\\d+$");
    }
}