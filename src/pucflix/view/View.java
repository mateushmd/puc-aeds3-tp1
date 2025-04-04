package pucflix.view;

public abstract class View 
{
    private Prompt prompt;

    public View(Prompt prompt)
    {
        this.prompt = prompt;
    }

    public abstract String getName();
    public abstract String getPrompt(int depth);
    public abstract String eval(int input, int depth) throws Exception;
}
