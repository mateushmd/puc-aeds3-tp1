package pucflix.view;

import java.util.Scanner;

import pucflix.model.*;

/**
 * Exibe a interface
 */
public class Prompt 
{
    Scanner scanner;

    private boolean isRunning;

    private View[] views;

    private int currentBranch;
    private int depth;

    public Prompt()
    {
        scanner = new Scanner(System.in);
        views = new View[] {
            new ShowsView(this),
        };
        currentBranch = -1;
    }

    /**
     * Inicia o ciclo de vida do programa
     */
    public void start()
    {   
        isRunning = true;

        while(isRunning)
        {
            show();
            eval();
        }
    }   
    
    private void show()
    {
        printHeader();

        if(currentBranch >= 0)
        {
            System.out.println(views[currentBranch].getPrompt(0));
        }
        else
        {
            for(int i = 0; i < views.length; i++) 
                System.out.println((i + 1) + ") " + views[i].getName());
        }

        System.out.println("0) Sair");
    }

    private void eval()
    {
        int opt = scanner.nextInt();

        if (opt < 0 || (currentBranch < 0 && opt > views.length))
            System.out.println("Opção inválida");

        if(opt == 0) 
        {
            isRunning = false;
            return;
        }

        if(currentBranch < 0)
        {
            currentBranch = opt - 1;
            return;
        }
        
        try { System.out.println(views[currentBranch].eval(opt, 0)); }
        catch(Exception ex) { System.out.println("Opção inválida"); }
    }

    private void printHeader()
    {
        System.out.println("PUCFlix 1.0");
        System.out.println("-----------");
        System.out.print("> Início");
        
        if(currentBranch >= 0)
            System.out.print(" > " + views[currentBranch].getName());

        System.out.println("\n");
    }

    public String askForInput(String message)
    {
        System.out.print(message);
        return scanner.nextLine();
    }

    public void close()
    {
        scanner.close();
    }
}
