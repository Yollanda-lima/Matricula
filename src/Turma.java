import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Turma {

	/*
	 * Cada turma com 20 alunos Alunos de cada turma realizam 4 atividades porsemestre OK 
	 * Todas as atividades valem 100 pontos
	 * Turmas divididas em nível de ensino
	 * Cada nível gera um código de turma (nível, dia da semana, turno) Ok
	 * Cada turma tem uma aula por semana e o semestre tem 20 semanas Ok
	 * Média das notasda turma Média de frequencia da turma
	 */

	protected final int QTD_PROVAS = 4;
	protected final double NOTA_MAX = 100;
	protected int codTurma;
	protected final int SEMANAS = 20;
	protected final int AULAS_POR_SEMANA = 1;
	protected int diaDaSemana;
	protected int turno;
	protected int nivel;
	private DayOfWeek diaSemana;
	private Periodo periodo;
	protected ArrayList<Aluno> alunos;
	private static Comparator<Aluno> ordemAlfabetica = new
	Comparator<Aluno>() {
		@Override
		public int compare(Aluno aluno1, Aluno aluno2) {
			return aluno1.getNome().compareTo(aluno2.getNome());
		}
	};

	Turma(int nivel, int diaDaSemana, int turno) { 
		this.alunos = new ArrayList<Aluno>();; 
		this.nivel = nivel;
		this.diaDaSemana = diaDaSemana;
		this.turno = turno;
		
		
		String juntar = "";
		juntar.concat(paraString(nivel));
		juntar.concat(paraString(diaDaSemana));
		juntar.concat(paraString(turno));
		
		this.codTurma = paraInteiro(juntar);
	}

	protected String paraString(int inteiro) {
		String nova = Integer.toString(inteiro);
		return nova;
	}
	
	protected int paraInteiro(String palavra) {
		int novo = Integer.parseInt(palavra);
		return novo;
	}
	
	public Turma(int nivel, DayOfWeek diaSemana, Periodo periodo) {
		this.nivel = nivel;
		this.diaSemana = diaSemana;
		this.periodo = periodo;
	}
	
	public enum Periodo {
		manha, tarde, noite;
	}
	

	public String relatorioTurma() {
		Collections.sort(this.alunos, ordemAlfabetica);
		String relatorio = "Relatorio: \n";
		for (Aluno matricula : alunos) {
			relatorio += matricula.toString() + "\n";
		}
		return relatorio;
	}
	
	public void relat() {
		System.out.printf("Codigo da turma: %s Media de notas: %.2f Media de Frequancia: %.2f " , getCodigoDaTurma(), mediaNotas(), mediaFrequencia());
		System.out.println(getMelhor());
		alunos();
	}
	
	public void alunos() {
		for (Aluno matricula : alunos) {
			System.out.printf("Matricula:", matricula);
		}
	}

	public Aluno getMelhor() {
		Aluno melhor = alunos.get(0);
		for (Aluno matricula : alunos) {
			if (melhor.getDesempenho() < matricula.getDesempenho()) {
				melhor = matricula;
			}
		}
		return melhor;
	}

	public int getCodigoDaTurma() {
		int codigo = this.nivel;
		codigo += this.diaSemana.getValue();
		codigo += this.getCodidoDoPeriodo();
		return codigo;
	}

	private int getCodidoDoPeriodo() {
		if (this.periodo == Periodo.manha) {
			return 3;
		} else if (this.periodo == Periodo.tarde) {
			return 2;
		} else if (this.periodo == Periodo.noite) {
			return 1;
		}
		return -1;
	}

	public boolean cadastroAlunos(Aluno matricula) {
		if (alunos.size() < 30) {
			boolean cadastro = matricula.setTurma(null);

			if (cadastro == true) {
				alunos.add(matricula);
				return true;
			} else {
				System.out.println("Cadastrado!");
				return false;
			}

		}
		System.out.println("Sem vaga!");
		return false;
		
	}
	

	public double mediaNotas() {
		double media = 0;
		for (Aluno matricula : alunos) {
			media += matricula.getNotaTotal();
		}
		media /= alunos.size();
		return media;
	}
	
	public double mediaFrequencia() {
		double media = 0;
		for (Aluno matricula : alunos) {
			media += matricula.freq();
		}
		media /= alunos.size();
		return media;
	}

	public void diaDaProva() {
		for (Aluno matricula : alunos) {
			matricula.fazerAvaliacao();
		}
	}
	
	
	@Override
	public String toString() {
		return "Turma" + this.codTurma + "| Alunos Matriculados: " + this.alunos.size();
	}
	
	
}
