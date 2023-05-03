public class DiscardPile {

        private Card card;
        private int pull;  // inform vom ziehen


        public DiscardPile(Card k){
            card = k;
            pull = 1;

        }

        public Card getCard(){
            return card;
        }

        public void layCard(Card k){
            card= k;
        }

        public int getpull(){
            return pull;
        }

        public void addPull(int z){
            if(pull== 1){
                pull = 0;
            }
            pull += z;
        }

        public void resetPull(){
          pull = 1;
        }


    }

