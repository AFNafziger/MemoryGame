package edu.wm.cs.cs301.memorygame;

public class GreekAlphabet implements Alphabet {
@Override //https://www.geeksforgeeks.org/overriding-in-java/
	public char[] toCharArray() {
		//returning the Greek alphabet, tried to remove symbols that look like their lowecase versions
		return new char[] {'Γ', 'Δ', 'Ε', 'Ζ', 'Η', 'Θ', 'Ι', 'Λ', 'Μ', 'Ν', 'Ξ', 'Ρ', 'Σ', 'Τ', 'Υ', 'Φ', 'Χ', 'Ψ', 'Ω', 'α', 'β', 'γ', 'δ', 'ε', 'ζ', 'η', 'ι', 'κ', 'λ', 'μ', 'ξ', 'ο', 'π', 'ρ','τ', 'φ', 'ψ', 'ω'};
}
}
