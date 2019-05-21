package com.jk.utils;

/*摩斯加密解密类*/
import java.util.StringTokenizer;

public class Morse {
    /*摩斯密码与字符之间的匹配常量*/
    final String A = ".-";
    final String B = "---.";
    final String C = "-.-.";
    final String D = "-..";
    final String E = ".";
    final String F = "..-.";
    final String G = "--.";
    final String H = "....";
    final String I = "..";
    final String J = ".---";
    final String K = "-.-";
    final String L = ".-..";
    final String M = "--";
    final String N = "-.";
    final String O = "---";
    final String P = ".--.";
    final String Q = "--.-";
    final String R = ".-.";
    final String S = "...";
    final String T = "-";
    final String U = "..-";
    final String V = "...-";
    final String W = ".--";
    final String X = "-..-";
    final String Y = "-.--";
    final String Z = "--..";
    final String $0 = "-----";
    final String $1 = ".----";
    final String $2 = "..---";
    final String $3 = "...--";
    final String $4 = "....-";
    final String $5 = ".....";
    final String $6 = "-....";
    final String $7 = "--...";
    final String $8 = "---..";
    final String $9 = "----.";
    final String period = ".-.-.-"; // .
    final String colon = "---..."; // :
    final String comma = "--..--"; // ,
    final String semicolon = "-.-.-."; // ;
    final String question = "..--.."; // ?
    final String equal = "-...-"; // =
    final String doubleQuotation = ".-..-.";// "
    final String singleQuotation = ".----.";// '
    final String slash = "-..-."; ///
    final String exclamation = "-.-.--"; // !
    final String hyphen = "-....-"; // -
    final String underscore = "..--.-"; // _
    final String lroundBrackets = "-.--."; // (
    final String rroundBrackets = "-.--.-"; // )
    final String $ = "...-..-"; // $
    final String ampersand = ".-..."; // &
    final String at = ".--.-."; // @
    final String plus = ".-.-."; // +
    /*加密对应的匹配*/
    public String matching(String str) {
        switch (str) {
            case "A":
                return A;
            case "B":
                return B;
            case "C":
                return C;
            case "D":
                return D;
            case "E":
                return E;
            case "F":
                return F;
            case "G":
                return G;
            case "H":
                return H;
            case "I":
                return I;
            case "J":
                return J;
            case "K":
                return K;
            case "L":
                return L;
            case "M":
                return M;
            case "N":
                return N;
            case "O":
                return O;
            case "P":
                return P;
            case "Q":
                return Q;
            case "R":
                return R;
            case "S":
                return S;
            case "T":
                return T;
            case "U":
                return U;
            case "V":
                return V;
            case "W":
                return W;
            case "X":
                return X;
            case "Y":
                return Y;
            case "Z":
                return Z;
            case "0":
                return $0;
            case "1":
                return $1;
            case "2":
                return $2;
            case "3":
                return $3;
            case "4":
                return $4;
            case "5":
                return $5;
            case "6":
                return $6;
            case "7":
                return $7;
            case "8":
                return $8;
            case "9":
                return $9;
            case ".":
                return period;
            case ":":
                return colon;
            case ",":
                return comma;
            case ";":
                return semicolon;
            case "?":
                return question;
            case "=":
                return equal;
            case "\"":
                return doubleQuotation;
            case "\'":
                return singleQuotation;
            case "/":
                return slash;
            case "!":
                return exclamation;
            case "-":
                return hyphen;
            case "_":
                return underscore;
            case "(":
                return lroundBrackets;
            case ")":
                return rroundBrackets;
            case "$":
                return $;
            case "&":
                return ampersand;
            case "@":
                return at;
            case "+":
                return plus;
        }
        return " ";
    }
    /*摩斯加密*/
    public String encryption(String password) {
        Fence f = new Fence();
        password = f.encryption(password);
        password = f.encryption(password);
        String MorsePasswork = new String();
        String mp = new String();
        for (int i = 0; i < password.length(); i++) {
            mp = mp.valueOf(password.charAt(i));
            MorsePasswork += matching(mp) + "/";
        }
        return MorsePasswork;
    }
    /*解密对应换的匹配*/
    public String matching2(String str) {
        if (str.equals(A))
            return "A";
        else if (str.equals(B))
            return "B";
        else if (str.equals(C))
            return "C";
        else if (str.equals(D))
            return "D";
        else if (str.equals(E))
            return "E";
        else if (str.equals(F))
            return "F";
        else if (str.equals(G))
            return "G";
        else if (str.equals(H))
            return "H";
        else if (str.equals(I))
            return "I";
        else if (str.equals(J))
            return "J";
        else if (str.equals(K))
            return "K";
        else if (str.equals(L))
            return "L";
        else if (str.equals(M))
            return "M";
        else if (str.equals(N))
            return "N";
        else if (str.equals(O))
            return "O";
        else if (str.equals(P))
            return "P";
        else if (str.equals(Q))
            return "Q";
        else if (str.equals(R))
            return "R";
        else if (str.equals(S))
            return "S";
        else if (str.equals(T))
            return "T";
        else if (str.equals(U))
            return "U";
        else if (str.equals(V))
            return "V";
        else if (str.equals(W))
            return "W";
        else if (str.equals(X))
            return "X";
        else if (str.equals(Y))
            return "Y";
        else if (str.equals(Z))
            return "Z";
        else if (str.equals($0))
            return "0";
        else if (str.equals($1))
            return "1";
        else if (str.equals($2))
            return "2";
        else if (str.equals($3))
            return "3";
        else if (str.equals($4))
            return "4";
        else if (str.equals($5))
            return "5";
        else if (str.equals($6))
            return "6";
        else if (str.equals($7))
            return "7";
        else if (str.equals($8))
            return "8";
        else if (str.equals($9))
            return "9";
        else if (str.equals(period))
            return ".";
        else if (str.equals(colon))
            return ":";
        else if (str.equals(comma))
            return ",";
        else if (str.equals(semicolon))
            return ";";
        else if (str.equals(question))
            return "?";
        else if (str.equals(equal))
            return "=";
        else if (str.equals(doubleQuotation))
            return "\"";
        else if (str.equals(singleQuotation))
            return "\'";
        else if (str.equals(slash))
            return "/";
        else if (str.equals(exclamation))
            return "!";
        else if (str.equals(hyphen))
            return "-";
        else if (str.equals(underscore))
            return "_";
        else if (str.equals(lroundBrackets))
            return "(";
        else if (str.equals(rroundBrackets))
            return ")";
        else if (str.equals($))
            return "$";
        else if (str.equals(ampersand))
            return "&";
        else if (str.equals(at))
            return "@";
        else if (str.equals(plus))
            return "+";
        else
            return " ";
    }
    /*摩斯解密*/
    public String decode(String MorsePassword) {
        String password = new String();
        Fence f = new Fence();
        StringTokenizer p = new StringTokenizer(MorsePassword, "/");
        while (p.hasMoreElements()) {
            password += matching2(p.nextToken());
        }
        password = f.decode(password);
        password = f.decode(password);
        return password;
    }
}
