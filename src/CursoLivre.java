import java.time.DayOfWeek;

public class CursoLivre extends Turma {
	
	public CursoLivre(int nivel, DayOfWeek diaSemana, Periodo periodo) {
		super(nivel, diaSemana, periodo);
	}
	
	@Override
	public boolean cadastroAlunos(Aluno matricula) {
		boolean cadastro = matricula.setTurma(null);
		
		if (cadastro == true) {
			alunos.add(matricula);
			return true;
		}else {
			System.out.println("Cadastrado");
			return false;
		}
	}
	
	@Override
	public void relat() {
		System.out.printf("Codigo: %s Media Notas EAD: %.2f", this.nivel + "L", mediaNotas());
		System.out.println("Aluno com Melhor Desempenho:  " + getMelhor());
		alunos();
	}
	
	@Override
	public void alunos() {
		for (Aluno matricula : alunos) {
			System.out.printf("Aluno: ", matricula.getNome() + "" + matricula.alunoAprovadoCursoLivre());
		}
	}
	
	
}
