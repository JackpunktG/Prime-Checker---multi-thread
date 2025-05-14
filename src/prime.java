class prime implements Runnable
{

    long primeLower, primeUpper;
    Thread t;
    String name;
    public static int ID = 2;
    public static int option = 0;               // to be in either range or single mode

    public static long singlePrime = 0;        // Prime for single number search

    prime(long lower, long upper)
    {
        setprime(lower, upper);
        t = new Thread(this, "No." + ID + " " + lower + " - " + upper);         // name is the range of numbers given to either search if prime, or check against prime
        System.out.println(t);
        ID++;

    }

    void setprime(long lower, long upper)
    {
        this.primeLower = lower;
        this.primeUpper = upper;
    }

    static boolean isprime(long prime)     //method for prime checking if entered number is prime range mode
    {

        if (prime == 1) return true;
        if (prime == 2) return true;

        if (prime % 2 == 0) return false;
        long halfprime = prime / 2;

        for (long i = 3; i <= halfprime; i += 2) {
            if (prime % i == 0) return false;

        }
        return true;
    }

    static boolean isprimeSingle(long primeLower, long primeUpper)           //Single seaker mode, to divide a number up into test ranges dynamically based on number of threads
    {
        if (primeLower % 2 == 0) primeLower += 1;   //checking to start on odd number

        for (primeLower = primeLower; primeLower <= primeUpper; primeLower += 2) {
            if (singlePrime % primeLower == 0) return false;
        }
        return true;
    }

    public void run()
    {
        if (option == 1) {
            for (primeLower = primeLower; primeLower <= primeUpper; primeLower++) {
                if (isprime(primeLower) == true) System.out.println(primeLower + " is PRIME!!" + " by thread: " + Thread.currentThread().getName()); //Input is number to be checked
            }
        }
        if (option == 2) {
            if (isprimeSingle(primeUpper, primeLower) == false) {                       //Input is range from lower to higher
                    System.out.println(primeUpper + " Nope... not prime. Found by Thread: " + ID);
                }

            }
    }
}
