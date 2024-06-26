/*
Nome: Erik Samuel Viana Hsu						RA: 10403109

Nome: Mateus Kenzo Iochimoto					RA: 10400995

Nome: Rodrigo Machado de Assis Oliveira de Lima	RA: 10401873

Nome: Thiago Shihan Cardoso Toma				RA: 10400764 

Turma 05G

*/

import java.util.*;

public class Analise {
	private String exp;
	private int i;
	
	public Analise(String exp) {
		this.exp = exp;
		this.i = 0;
	}
	
	public boolean parse() {
        i = 0;
        int count = 0;
        boolean result = I();
        
        while(i< exp.length() && exp.charAt(i) != ' ') {
        	if(exp.charAt(i) == '(') {
        		count++;
        	} else if (exp.charAt(i) == ')') {
        		count--;
        		if(count < 0) {
        			return false;
        		}
        	}
        	i++;
        }
        return count == 0 && result;
    }

    private boolean I() {
        return S();
    }

    private boolean S() {
        return T() && K();
    }

    private boolean K() {
        if (i < exp.length() && (exp.charAt(i) == '+' || exp.charAt(i) == '-')) {
            i++;
            return T() && K();
        }
        return true;
    }

    private boolean T() {
        return F() && Z();
    }

    private boolean Z() {
        if (i < exp.length() && (exp.charAt(i) == '*' || exp.charAt(i) == '/')) {
            i++;
            return F() && Z();
        }
        return true;
    }

    private boolean F() {
        if (i < exp.length()) {
            if (exp.charAt(i) == '(') {
                i++;
                boolean result = S();
                if (i < exp.length() && exp.charAt(i) == ')') {
                    i++;
                    return result;
                }
                return false;
            } else if (Character.isDigit(exp.charAt(i))) {
                return N();
            } else if (exp.charAt(i) == '-') {
                i++;
                return N();
            }
        }
        return false;
    }

    private boolean N() {
        if (i < exp.length() && Character.isDigit(exp.charAt(i))) {
            i++;
            return D();
        }
        return false;
    }

    private boolean D() {
        if (i < exp.length() && Character.isDigit(exp.charAt(i))) {
            i++;
            return D();
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite uma expressão aritmética: ");
        String expressao = scanner.nextLine();
        Analise analise = new Analise(expressao);
        if (analise.parse()) {
            System.out.println("A expressão é válida.");
        } else {
            System.out.println("A expressão é inválida.");
        }
    }
}
