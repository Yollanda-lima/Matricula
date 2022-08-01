import java.time.DayOfWeek;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class Aluno {
	
	/*
	 * Um aluno sera aprovado se conseguir no mínimo 60 pontos e 75% de frequencia Ok
	 */

	protected final int QTD_MAX_TURMAS = 1;
	protected double notaTotal;
	protected double frequencia;
	protected String nome;
	protected boolean aprovacao;
	protected Object turma;
	private int nivel;
	private Periodo periodo;
	private Vector<Integer> notas = new Vector<Integer>();
	private Object d;
	Scanner entrada = new Scanner(System.in);

	Aluno(String nome, int turma, int matricula) {
		this.nome = nome;
		this.turma = turma;
	}
	
	public Aluno(int matricula, String nome, int turma) {
		this.nome = nome;
		this.turma = null;
		this.frequencia = -2;
	}


	void adicionarNotas() {
		double notaTotal = 0;
		for(int i=0; i<4; i++) {
			double nota = entrada.nextDouble();
			if(nota<0 || nota>25) {
				nota=0;
		}
		notaTotal += nota;
	}
		this.notaTotal = notaTotal;
	}


	void validarAprovado() {
		if ((this.notaTotal >= 60) && (this.frequencia >= 70.0)) {
			this.aprovacao = true;
		} else
			this.aprovacao = false;
	}
	
	public void fazerAvaliacao() {
		Random rand = new Random();
		int upperbound = 16;
		int int_random = rand.nextInt(upperbound) + 10;
		notas.add(int_random);
	}
	
	public double freq() {
		if (frequencia == -2) {
			Random rand = new Random();
			int upperbound = 101;
			for (int i = 0; i < 30; i++) {
				int int_random = rand.nextInt(upperbound);
				if (int_random > 20) {
					frequencia++;
				}
			}
			frequencia /= 20.0;
		}
		return frequencia;
	}
	public String getCodTurma() {
		String cod = "";
		cod += this.nivel;
		cod += ((DayOfWeek) this.d).getValue();
		cod += this.getCodPeriodo();
		return cod;
	}

	private int getCodPeriodo() {
		if (this.periodo == Periodo.manha) {
			return 1;
		} else if (this.periodo == Periodo.tarde) {
			return 2;
		} else if (this.periodo == Periodo.noite) {
			return 3;
		}
		return -1;
	}

	public enum Periodo {
		manha, tarde, noite;
	}


	public int getDesempenho() {
		return (int) (0.8 * getNotaTotal() + 0.2 * frequencia);
	}


	public double getNotaTotal() {
		return this.verificarAprovacao();
	}


	public boolean setTurma(Turma turma) {
		if (this.turma == null) {
			this.turma = turma;
			return true;
		} else {
			return false;
		}
	}

	private double verificarAprovacao() {
		double media = 0;
		for (Integer nota : notas) {
			media += nota;
		}
		return media;
	}
	
	public String alunoAprov() {
		this.validarAprovado();
		if(this.aprovacao) {
			return "Aluno Aprovado";
		}
		return "Aluno Reprovado";
	}
	
	public boolean alunoAprovadoCursoLivre() {
		int aulasVizualizadas = 100;
		if(aulasVizualizadas < 100) {
			System.out.println("Reprovado");
		}else {
			System.out.println("Aprovado");
		}
		return aprovacao;
	}
	
	@Override
	public String toString() {
		return this.nome + "Nota total: " + this.getNotaTotal() + "" + this.alunoAprov();
	}
	
	public String getNome() {
		return this.nome;
	}
}
