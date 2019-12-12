package tradutor1;

public class AvlNode {
	protected Dicionario chave;
	protected int altura;
	protected AvlNode esquerda, direita;
	
	public AvlNode(Dicionario chave) {
		this(chave,null,null);	
	}
	
	public AvlNode(Dicionario chave, AvlNode esq, AvlNode dir) {
		this.chave = chave;
		this.esquerda = esq;
		this.direita = dir;
		this.altura = 0;
	}
	
	public String toString() {
		return chave.toString();
	}
}
