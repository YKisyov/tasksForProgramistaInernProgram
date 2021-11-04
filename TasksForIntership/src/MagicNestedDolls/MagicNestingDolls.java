package MagicNestedDolls;

public class MagicNestingDolls {

    int getLastNestingDoll (int[] nestingDollsElements) {
        // sample data for preview and think over purposes: {-1, 1, 2, 3, 5, 5, 3, 7};

        int nightsCounter = 1; // Used to determine whether a night is odd or even
       while (nestingDollsElements.length > 1){
           //Logical action here
           int[] tmpArray = new int[nestingDollsElements.length / 2];
           if (nightsCounter % 2 != 0){
               //This is an odd night, so we have to "sum" the consecutive dolls:
               for (int i = 0; i < tmpArray.length;) {
                   for (int j = 0; j < nestingDollsElements.length; j+=2) {
                       tmpArray[i++] = nestingDollsElements[j] + nestingDollsElements[j+1];
                  }
               }
           } else {
               //This is an EVEN night, so we have to multiply the consecutive dolls content:
               for (int i = 0; i < tmpArray.length; i++) {
                   for (int j = 0; j < nestingDollsElements.length; j+=2) {
                       tmpArray[i++] = nestingDollsElements[j] * nestingDollsElements[j+1];
                   }
               }
           }
           /*
           Please note that is safe to use the j+1, as per the task rules, we always have an even number of dolls
           just because 2^k always yields an even result, there is only one exception - when k = 0, but then we have
           only one doll present in the "row" and we have taken care for this corner case in the very begging of the
           current method. The purpose of the method is to actually force reduce the number of doll to exactly 1;
           */
           nestingDollsElements = tmpArray; //managing the references
           nightsCounter++;
       }
       return nestingDollsElements[0];
    }

}
