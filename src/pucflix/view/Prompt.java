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

    private File[] branches;

    private int currentBranch;

    public Prompt()
    {
        scanner = new Scanner(System.in);
        branches = new File[] {
            new ShowsFile(),
            new EpisodesFile(),
            new ActorsFile()
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
        System.out.println("PUCFlix 1.0");
        System.out.println("-----------");
        System.out.print("> Início");

        if(currentBranch >= 0)
            System.out.print(" > " + branches[currentBranch].getName());

        System.out.println("\n");

        if(currentBranch >= 0)
        {
            System.out.println("1) Incluir");
            System.out.println("2) Buscar");
            System.out.println("3) Alterar");
            System.out.println("4) Excluir");
            System.out.println("0) Retornar");
            return;
        }

        for(int i = 0; i < branches.length; i++) System.out.println((i + 1) + ") " + branches[i].getName());
        System.out.println("0) Sair");
    }

    private void eval()
    {
        int opt = scanner.nextInt();

        while(opt < 0 || (currentBranch >= 0 && opt > 4) || opt > 3)
        {
            System.out.println("Opção inválida");
            opt = scanner.nextInt();
        }

        if(currentBranch < 0)
        {
            if(opt == 0) isRunning = false;

            currentBranch = opt - 1;
            return;
        }

        switch(opt)
        {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            default:
                currentBranch = -1;
                break;
        }
    }

    public void close()
    {
        scanner.close();
    }
}