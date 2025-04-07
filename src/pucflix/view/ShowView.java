package pucflix.view;

import pucflix.entity.Show;
import pucflix.model.ShowFile;

public class ShowView extends View 
{
    private ShowFile file; 

    public ShowView(Prompt prompt) throws Exception
    {
        super(prompt);
        file = new ShowFile();
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
            {
                String name = prompt.askForInput("Nome: ");
                int releaseYear = Integer.parseInt(prompt.askForInput("Ano de lançamento: "));
                String sinopsys = prompt.askForInput("Sinopse: ");
                String streaming = prompt.askForInput("Serviço de streaming: ");
                Show show = new Show(name, releaseYear, sinopsys, streaming); 
                file.create(show);
                return "Operação finalizada com sucesso";
            }
            case 2:
            {
                String search = prompt.askForInput("Busca: ");

                try
                {
                    int id = Integer.parseInt(search);
                    System.out.println(file.read(id).toString());
                }
                catch(Exception ex)
                {
                    Show[] shows = file.read(search);
                    if(shows.length == 1)
                    {
                        return shows[0].toString();
                    }
                    
                    String result = "";

                    for(Show s: shows)
                        result += s.getName() + " (" + s.getID() + ")\n";

                    return result;
                }
            }
            case 3: 
            {
                int id = Integer.parseInt(prompt.askForInput("ID: "));
                
                Show show = file.read(id);

                String name = prompt.askForInput("Nome (vazio para não alterar): ");
                if(!name.isEmpty())
                    show.setName(name);
                    
                String strYear = prompt.askForInput("Ano de lançamento (vazio para não alterar): ");
                if(!strYear.isEmpty())
                    show.setReleaseYear(Integer.parseInt(strYear));

                String synopsis = prompt.askForInput("Sinopse (vazio para não alterar): ");
                if(!synopsis.isEmpty())
                    show.setSynopsis(synopsis);

                String streaming = prompt.askForInput("Serviço de streaming (vazio para não alterar): ");
                if(!streaming.isEmpty())
                    show.setStreamingService(streaming);

                file.update(show);

                return "Operação finalizada com sucesso";
            }
            case 4:
            {
                int id = Integer.parseInt(prompt.askForInput("ID: "));
                
                file.delete(id);
                
                return "Operação finalizada com sucesso";
            }
        }

        throw new Exception();
    }
}
