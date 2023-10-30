public class Estoque {
    private int codigoProduto;
    private String nome;
    private int qtde;
    private float valorUnitario;

    public Estoque() {

    }

    public Estoque(int codigoProduto, String nome, int qtde, float valorUnitario) {
        this.codigoProduto = codigoProduto;
        this.nome = nome;
        this.qtde = qtde;
        this.valorUnitario = valorUnitario;
    }

    public int getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(int codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }

    public float getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(float valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

}
