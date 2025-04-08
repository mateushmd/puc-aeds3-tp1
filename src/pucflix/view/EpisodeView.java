package pucflix.view;

import pucflix.entity.Episode;
import pucflix.model.EpisodeFile;
import java.time.LocalDate;

public class EpisodeView extends View 
{
    private EpisodeFile file; 

    public EpisodeView(Prompt prompt) throws Exception
    {
        super(prompt);
        file = new EpisodeFile();
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
    public void eval(int input, int depth) throws Exception
    {
        switch(input)
        {
            case 1: 
                String name = prompt.askForInput("Nome: ");
                int season = Integer.parseInt(prompt.askForInput("Temporada: "));
                int day = Integer.parseInt(prompt.askForInput("Dia do lançamento: "));
                int month = Integer.parseInt(prompt.askForInput("Mês do lançamento: "));
                int year = Integer.parseInt(prompt.askForInput("Ano do lançamento: "));
                float durationTime = Float.parseFloat(prompt.askForInput("Tempo de duração: "));
                Episode episode = new Episode(name, season, LocalDate.of(year, month, day), durationTime); 
            case 2: System.out.println("Searching");
            case 3: System.out.println("Updating");
            case 4: System.out.println("Deleting");
        }
    }
}
