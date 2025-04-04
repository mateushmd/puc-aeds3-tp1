package pucflix.view;


public class ShowsView extends View 
{
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
            case 1: return "Inserting";
            case 2: return "Searching";
            case 3: return "Updating";
            case 4: return "Deleting";
        }

        throw new Exception();
    }
}
