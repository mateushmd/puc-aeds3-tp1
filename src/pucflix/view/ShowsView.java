package pucflix.view;

import pucflix.entity.Show;

public class ShowsView extends View 
{
    private ShowsFile file = new ShowsFile(); 

    public ShowsView(Prompt prompt)
    {
        super(prompt);
    }

    @Override
    public String getName()
    {
        return "Séries";
    }

    @Override
    public String getPrompt(int depth)
    {
        return
            "1) Incluir\n" +
            "2) Buscar\n" +
            "3) Alterar\n" +
            "4) Excluir";
    }

    @Override
    public String eval(int input, int depth) throws Exception
    {
        switch(input)
        {
            case 1: 
                String name = prompt.askForInput("Nome: ");
                int releaseYear = Integer.parseInt(prompt.askForInput("Ano de lançamento: "));
                String sinopsys = prompt.askForInput("Sinopse: ");
                String streaming = prompt.askForInput("Serviço de streaming: ");
                Show show = new Show(name, releaseYear, sinopsys, streaming); 
            case 2: return "Searching";
            case 3: return "Updating";
            case 4: return "Deleting";
        }

        throw new Exception();
    }
}
