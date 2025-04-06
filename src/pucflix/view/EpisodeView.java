package pucflix.view;

import pucflix.entity.Episode;

public class EpisodeView extends View 
{
    private EpisodeFile file = new ShowsFile(); 

    public EpisodeView(Prompt prompt)
    {
        super(prompt);
    }

    @Override
    public String getName()
    {
        return "Episódios";
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
                int season = prompt.askForInput("Temporada: ");
                int releaseDate = Integer.parseInt(prompt.askForInput("Data de lançamento: "));
                float durationTime = prompt.askForInput("Tempo de duração: ");
                Episode episode = new Episode(name, season, releaseDate, durationTime); 
            case 2: return "Searching";
            case 3: return "Updating";
            case 4: return "Deleting";
        }

        throw new Exception();
    }
}