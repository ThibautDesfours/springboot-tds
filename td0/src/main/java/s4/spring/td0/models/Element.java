package s4.spring.td0.models;


public class Element {
	private String nom="";
	private int evaluation;
	
	public Element() {
		this.nom="Pas de nom";
		this.evaluation=0;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getEvaluation() {
		return evaluation;
	}
	public void setEvaluation(int evaluation) {
		this.evaluation = evaluation;
	}
	
	@Override
	public boolean equals(Object object) {
		if(object == null)
			return false;
		
		if(!(object instanceof Element))
			return false;
		if(nom == null) {
			return ((Element)(object)).getNom() == null;
		}
		return nom.equals(((Element)(object)).getNom());
	}
}
