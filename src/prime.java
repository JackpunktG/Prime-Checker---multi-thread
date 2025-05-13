class prime implements Runnable
{

    long primeLower, primeUpper;
    Thread t;
    String name;
    public static int ID = 2;

    prime(long lower, long upper)
    {
        setprime(lower, upper);
        t = new Thread(this, "No." + ID + " " + lower + " - " + upper);
        System.out.println(t);
        ID++;

    }

    void setprime(long lower, long upper)
    {
        this.primeLower = lower;
        this.primeUpper = upper;
    }

    static boolean isprime(long prime)
    {

        if (prime == 1) return true;
        if (prime == 2) return true;

        if (prime % 2 == 0) return false;
        long halfprime = prime / 2;

        for (int i = 3; i <= halfprime; i += 2) {
            if (prime % i == 0) return false;

        }
        return true;
    }

    public void run()
    {

        for (primeLower = primeLower; primeLower <= primeUpper; primeLower++) {
            //if (isprime(primeLower) == true);
            if (isprime(primeLower) == true) System.out.println(primeLower + " is PRIME!!" + " by thread: " + Thread.currentThread().getName());
        }
    }
}
