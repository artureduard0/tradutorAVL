package tradutor1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tradutor {
	private AVL avl = new AVL();

	protected void carregaDicionario(File arq) throws IOException {
		if (arq.canWrite() == true) {
			try {
				BufferedReader inputStream = new BufferedReader(new FileReader("E:/Artur Eduardo/dicionario.dat"));
				String line;
				while ((line = inputStream.readLine()) != null) {
					String[] s = line.split("#");
					List<String> definicoes = new ArrayList<>();
					for (int i = 1; i < s.length; i++) {
						definicoes.add(s[i]);
					}
					Dicionario d = new Dicionario(s[0], definicoes);
					avl.insert(d);
					//avl.displayTree();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private List<String> inserirPalavra(String palavra) {
		List<String> def = new ArrayList<>();
		// qual os significados?
		System.out.println("Informe o(s) significados (S para encerrar): ");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String str;
			do {
				str = br.readLine();
				if(str.equals("S"))
					break;
				def.add(str);
			} while (!str.equals("S"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// insere
		Dicionario d = new Dicionario(palavra, def);
		avl.insert(d);
		return def;
	}

	public List<String> traduzPalavra(String palavra) {
		AvlNode retorno = avl.search(palavra);
		char resp = ' ';

		// se não existir a palavra, pergunta se deseja inserir e chama o método
		if (retorno == null) {
			System.out.println("Essa palavra não existe! Você deseja inseri-la?");
			System.out.println("Digite S para sim ou N para não:");
			Scanner s = new Scanner(System.in);
			resp = s.next().charAt(0);
			if (resp == 'S' || resp == 's')
				return inserirPalavra(palavra);
			else
				return null;
		}
		// a palavra existe, então traduzir e retornar a lista
		else {
			List<String> def = retorno.chave.getLista();
			return def;
		}
	}

	public void insereTraducao(String palavra, List<String> definicoes) {
		AvlNode retorno = avl.search(palavra);
		Scanner s = new Scanner(System.in);
		// se existir, insere a definição
		if (retorno != null) {
			for (int i = 0; i < retorno.chave.getLista().size(); i++) {
				for (int j = 0; j < definicoes.size(); j++) {
					if (retorno.chave.getLista().get(i).equals(definicoes.get(j)))
						return;
					else
						retorno.chave.getLista().add(definicoes.get(j));
				}
			}
		}
		// se não, pergunta se deseja criar a palavra
		else {
			char resp = ' ';
			System.out.println("Essa palavra não existe! Você deseja inseri-la?");
			System.out.println("Digite S para sim ou N para não:");
			resp = s.next().charAt(0);
			if (resp == 'S' || resp == 's')
				inserirPalavra(palavra);
			else
				return;
		}
	}

	public void salvaDicionario(File file,BufferedWriter bw) throws IOException {
		avl.exportar(file,bw);
	}
}
