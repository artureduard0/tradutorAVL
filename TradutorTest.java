package tradutor1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TradutorTest {
	public static void main(String args[]) {
		Tradutor t = new Tradutor();
		
		//endereço do arquivo
		File file = new File("E:/Artur Eduardo/dicionario.dat");
		try {
			
			//carrega o dicionário do arquivo
			t.carregaDicionario(file);
			
			//traduz a palavra "husband" e retorna a sua lista
			//List<String> lista = t.traduzPalavra("husband");
			//System.out.println(lista.toString());
			
			//tenta inserir uma palavra não existe
			//List<String> lista2 = t.traduzPalavra("true");

			//insere uma tradução "teste" para "husband"
			/*List<String> l = new ArrayList<String>();
			l.add("abc");
			t.insereTraducao("husband", l);
			t.insereTraducao("abc", l);*/
			
			//exporta o conteúdo da AVL para o arquivo
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
				t.salvaDicionario(file,bw);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
