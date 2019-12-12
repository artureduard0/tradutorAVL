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
		
		//endere�o do arquivo
		File file = new File("E:/Artur Eduardo/dicionario.dat");
		try {
			
			//carrega o dicion�rio do arquivo
			t.carregaDicionario(file);
			
			//traduz a palavra "husband" e retorna a sua lista
			//List<String> lista = t.traduzPalavra("husband");
			//System.out.println(lista.toString());
			
			//tenta inserir uma palavra n�o existe
			//List<String> lista2 = t.traduzPalavra("true");

			//insere uma tradu��o "teste" para "husband"
			/*List<String> l = new ArrayList<String>();
			l.add("abc");
			t.insereTraducao("husband", l);
			t.insereTraducao("abc", l);*/
			
			//exporta o conte�do da AVL para o arquivo
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
				t.salvaDicionario(file,bw);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
