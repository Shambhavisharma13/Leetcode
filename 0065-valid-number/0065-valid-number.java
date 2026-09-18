
class Solution {
    public boolean isNumber(String s) {

        boolean digitSeen = false;
        boolean dotSeen = false;
        boolean exponentSeen = false;
        boolean digitAfterExponent = true;

        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);

            // If character is a digit
            if (ch >= '0' && ch <= '9') {
                digitSeen = true;

                // If we are after e/E, mark digit after exponent
                if (exponentSeen) {
                    digitAfterExponent = true;
                }
            }

            // Decimal point
            else if (ch == '.') {

                // Dot cannot appear twice
                // Dot cannot appear after e/E
                if (dotSeen || exponentSeen) {
                    return false;
                }

                dotSeen = true;
            }

            // Exponent
            else if (ch == 'e' || ch == 'E') {

                // e/E can appear only once
                // There must be a digit before e/E
                if (exponentSeen || !digitSeen) {
                    return false;
                }

                exponentSeen = true;

                // After e/E, we need at least one digit
                digitAfterExponent = false;
            }

            // Sign
            else if (ch == '+' || ch == '-') {

                // Sign is allowed only at the beginning
                // OR immediately after e/E
                if (i != 0 &&
                    s.charAt(i - 1) != 'e' &&
                    s.charAt(i - 1) != 'E') {
                    return false;
                }
            }

            // Anything else is invalid
            else {
                return false;
            }
        }

        // At least one digit overall
        // AND at least one digit after exponent if exponent exists
        return digitSeen && digitAfterExponent;
    }
}