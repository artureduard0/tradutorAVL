package tradutor1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AVL {
	AvlNode palavra = null;

	public AVL() {
		palavra = null;
	}

	// retorna se a árvore está vazia
	public boolean isEmpty() {
		return palavra == null;
	}

	// retorna a altura da árvore
	private int getAltura(AvlNode n) {
		return n == null ? -1 : n.altura;
	}

	// retorna o maior valor
	private int max(int x, int y) {
		return x > y ? x : y;
	}

	// retorna o fator de balanceamento da árvore com raiz n
	private int getFator(AvlNode n) {
		return this.getAltura(n.esquerda) - this.getAltura(n.direita);
	}

	public boolean insert(Dicionario d) {
		palavra = insert(d, palavra);
		return true;
	}

	private AvlNode insert(Dicionario d, AvlNode n) {
		if (n == null)
			n = new AvlNode(d, null, null);
		else if (d.getPalavra().compareTo(n.chave.getPalavra()) < 0)
			n.esquerda = this.insert(d, n.esquerda);
		else if (d.getPalavra().compareTo(n.chave.getPalavra()) > 0)
			n.direita = this.insert(d, n.direita);
		n = this.balanceamento(n);
		return n;
	}

	// faz o balanceamento
	public AvlNode balanceamento(AvlNode n) {
		if (this.getFator(n) == 2) {
			if (this.getFator(n.esquerda) > 0)
				n = rotacaoSimplesDireita(n);
			else
				n = rotacaoDuplaDireita(n);
		} else if (this.getFator(n) == -2) {
			if (this.getFator(n.direita) < 0)
				n = rotacaoSimplesEsquerda(n);
			else
				n = rotacaoDuplaEsquerda(n);
		}
		n.altura = max(this.getAltura(n.esquerda), this.getAltura(n.direita)) + 1;
		return n;
	}

	// faz a rotação simples a direita do nó recebido
	private AvlNode rotacaoSimplesDireita(AvlNode n2) {
		AvlNode n1 = n2.esquerda;
		n2.esquerda = n1.direita;
		n1.direita = n2;
		n2.altura = max(this.getAltura(n2.esquerda), this.getAltura(n2.direita)) + 1;
		n1.altura = max(this.getAltura(n1.esquerda), n2.altura) + 1;
		return n1;
	}

	// faz a rotação simples a esquerda do nó recebido
	private AvlNode rotacaoSimplesEsquerda(AvlNode n1) {
		AvlNode n2 = n1.direita;
		n1.direita = n2.esquerda;
		n2.esquerda = n1;
		n1.altura = max(this.getAltura(n1.esquerda), this.getAltura(n1.direita)) + 1;
		n2.altura = max(this.getAltura(n2.direita), n1.altura) + 1;
		return n2;
	}

	// faz a rotação dupla a direita do nó recebido
	private AvlNode rotacaoDuplaDireita(AvlNode n3) {
		n3.esquerda = this.rotacaoSimplesEsquerda(n3.esquerda);
		return this.rotacaoSimplesDireita(n3);
	}

	// faz a rotação dupla a esquerda do nó recebido
	private AvlNode rotacaoDuplaEsquerda(AvlNode n1) {
		n1.direita = this.rotacaoSimplesDireita(n1.direita);
		return this.rotacaoSimplesEsquerda(n1);
	}

	public AvlNode search(String p) {
		return search(palavra, p);
	}

	private AvlNode search(AvlNode p, String palavra) {
		while (p != null) {
			// palavra do nó atual
			String palavraNoAtual = p.chave.getPalavra();
			// se o nó atual for igual a palavra procurada retorna o nó
			if (palavraNoAtual.equals(palavra))
				return p;
			// se o valor da palavra do nó for menor que a comparada
			// procura na sub-árvore a esquerda
			else if (palavraNoAtual.compareTo(palavra) > 0)
				p = p.esquerda;
			// se o valor da palavra do nó for maior que a comparada
			// procura na sub-árvore a direita
			else
				p = p.direita;
		}
		// caso não exista
		return null;
	}

	private AvlNode acharPai(Dicionario d) {
		AvlNode p = palavra;
		AvlNode anterior = null;
		while (p != null && !(p.chave.equals(d))) { // acha o nó p com a chave el
			anterior = p;
			if (p.chave.getPalavra().compareTo(d.palavra) < 0)
				p = p.direita;
			else
				p = p.esquerda;
		}
		if (p != null && p.chave.equals(d))
			return anterior;
		return null;
	}

	public void exportar(File file, BufferedWriter bw) throws IOException {
		if (file == null || palavra == null)
			return;

		String pai = palavra.toString();
		bw.write(pai);
		bw.newLine();

		exportarSubArvore(palavra.esquerda, file, bw);
		exportarSubArvore(palavra.direita, file, bw);
	}

	private void exportarSubArvore(AvlNode node, File file, BufferedWriter bw) throws IOException {
		if (node != null) {
			AvlNode pai = this.acharPai(node.chave);
			if (node.equals(pai.esquerda) == true) {
				bw.write(node.toString());
				bw.newLine();
			} else {
				bw.write(node.toString());
				bw.newLine();
			}
			exportarSubArvore(node.esquerda, file, bw);
			exportarSubArvore(node.direita, file, bw);
		}
	}
}
