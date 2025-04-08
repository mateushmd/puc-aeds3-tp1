package pucflix.view;

import pucflix.entity.Show;
import pucflix.model.ShowFile;
import java.lang.NumberFormatException;

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
    public void eval(int input, int depth) throws Exception
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
                System.out.println("Operação finalizada com sucesso");
                break;
            }
            case 2:
            {
                String search = prompt.askForInput("Busca: ");
                Show[] shows = file.read(search);

                if(shows == null || shows.length == 0)
                    System.out.println("Nenhuma série encontrada");

                if(shows.length == 1)
                {
                    System.out.println(shows[0].toString());
                    break;
                }
                    
                String result = "";
                
                for(int i = 0; i < shows.length; i++)
                    result += (i + 1) + ") " + shows[i].getName() + "\n"; 

                System.out.println(result);

                int n = 0;
                boolean valid = false;

                while(!valid)
                {
                    try
                    {
                        n = Integer.parseInt(prompt.askForInput("Número: "));
                        if(n < 1 || n > shows.length) throw new Exception();
                        valid = true;
                    }
                    catch(Exception ex) { System.out.println("Insira um número válido"); }
                }

                System.out.println(shows[n - 1].toString());
                
                break;
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

                System.out.println("Operação finalizada com sucesso");
                break;
            }
            case 4:
            {
                int id = Integer.parseInt(prompt.askForInput("ID: "));
                
                if(!file.delete(id))
                    System.out.println("Nenhuma série com ID " + id + " encontrada");
                else 
                    System.out.println("Operação finalizada com sucesso");
                break;
            }
        }
    }
}
