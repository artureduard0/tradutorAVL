package tradutor1;

import java.util.ArrayList;
import java.util.List;

public class Dicionario {
	protected String palavra;
	protected List<String> definicoes;
	
	public Dicionario(String palavra, List<String> traducao) {
		this.palavra = palavra;
		this.definicoes = traducao;
	}
	
	public String getPalavra() {
		return this.palavra;
	}
	
	public List<String> getLista(){
		return this.definicoes;
	}

	@Override
	public String toString() {
		String def = "";
		for(int i = 0; i < definicoes.size(); i++)
			def=def+"#"+definicoes.get(i);
		return palavra + def;
	}
}