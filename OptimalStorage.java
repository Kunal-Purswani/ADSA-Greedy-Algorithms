import java.util.*;

class OptimalStorage {
    public int getIndex(int max, int[] arr) {
        int index = -1;
        for (int i = 0; i < arr.length; i++)
            index = max == arr[i] ? i : index;
        return index;
    }

    public int[] getStorageSeq(int[] seq, int[] time, int[] timeSorted) {
        for (int i = 0; i < time.length; i++)
            seq[i] = getIndex(timeSorted[i], time) + 1;
        return seq;
    }

    public int getTRT(int[] seq, int[] time) {
        int trt = 0;
        for (int i = 0; i < time.length; i++)
            trt += time[seq[i] - 1] * (time.length - i);
        return trt;
    }

    public double getMRT(int[] seq, int[] time, int programs) {
        double mrt = 0;
        int trt = getTRT(seq, time);
        mrt = (double) trt / programs;
        return mrt;
    }

    public int[] getSeq(Scanner sc, int[] seq, int programs) {
        seq = new int[programs];
        int program;
        for (int i = 0; i < programs; i++) {
            System.out.print("Enter program no. : ");
            do {
                program = Integer.parseInt(sc.nextLine());
                if (program < 1) {
                    System.out.println("Program no. should be greater than 0.\nEnter Program no. again: ");
                } else if (program > programs) {
                    System.out
                            .println("Program no. cannot be greater than " + programs + "\nEnter Program no. again: ");
                } else if (Arrays.toString(seq).indexOf("" + program) != -1) {
                    System.out.println(
                            "You have already inserted Program no. " + program + "\nEnter Program no. again: ");
                } else {
                    seq[i] = program;
                }
            } while (program < 1 || program > programs || Arrays.toString(seq).indexOf("" + program) == -1);
        }
        return seq;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        OptimalStorage os = new OptimalStorage();
        int trt = 0, programs;
        double mrt = 0;
        int[] time, timeSorted, seq;
        String str = "";
        do {
            System.out.print("\nEnter no. of programs: ");
            programs = Integer.parseInt(sc.nextLine());
            if (programs < 1)
                System.out.println("No. of programs should be greater than zero.");
        } while (programs < 1);
        time = new int[programs];
        timeSorted = new int[programs];
        for (int i = 0; i < programs; i++) {
            System.out.print("Enter Retrieval time of Program " + (i + 1) + ": ");
            time[i] = Integer.parseInt(sc.nextLine());
            timeSorted[i] = time[i];
        }
        Arrays.sort(timeSorted);
        seq = new int[programs];
        while (true) {
            System.out.println(
                    "\nProgrammed by Kunal Purswani Roll No. 59.\n\n----------------------- MENU -------------------------\n\tPress 1 for Optimal Storage Sequence\n\tPress 2 for Total Retrieval Time of a Sequence\n\tPress 3 for Mean Retrieval Time of a Sequence\n\tPress 4 to Exit.\n------------------------------------------------------\n");
            System.out.print("Enter your choice: ");
            int ch = Integer.parseInt(sc.nextLine());
            switch (ch) {
                case 1:
                    seq = os.getStorageSeq(seq, time, timeSorted);
                    str = "Optimal Storage Sequence: ";
                    for (int i = 0; i < seq.length; i++) {
                        if (seq[i] > 0)
                            str += "P" + seq[i] + ", ";
                    }
                    str = str.substring(0, str.length() - 2);
                    System.out.println(str);
                    trt = os.getTRT(seq, time);
                    System.out.println("Total Retrieval Time: " + trt);
                    mrt = os.getMRT(seq, time, programs);
                    if (Math.floor(mrt) - mrt == 0)
                        System.out.println("Mean Retrieval Time: " + (int) Math.floor(mrt));
                    else {
                        mrt = Double.parseDouble(String.format("%.3f", mrt));
                        System.out.println("Mean Retrieval Time: " + mrt);
                    }
                    break;
                case 2:
                    System.out.println("Enter Sequence of Programs:");
                    seq = os.getSeq(sc, seq, programs);
                    System.out.println(seq[0]);
                    str = "\nYour Sequence: ";
                    for (int i = 0; i < seq.length; i++) {
                        if (seq[i] > 0)
                            str += "P" + seq[i] + ", ";
                    }
                    str = str.substring(0, str.length() - 2);
                    System.out.println(str);
                    trt = os.getTRT(seq, time);
                    System.out.println("Total Retrieval Time: " + trt);
                    break;
                case 3:
                    System.out.println("Enter Sequence of Programs:");
                    seq = os.getSeq(sc, seq, programs);
                    str = "\nYour Sequence: ";
                    for (int i = 0; i < seq.length; i++) {
                        if (seq[i] > 0)
                            str += "P" + seq[i] + ", ";
                    }
                    str = str.substring(0, str.length() - 2);
                    System.out.println(str);
                    mrt = os.getMRT(seq, time, programs);
                    if (Math.floor(mrt) - mrt == 0)
                        System.out.println("Mean Retrieval Time: " + (int) Math.floor(mrt));
                    else {
                        mrt = Double.parseDouble(String.format("%.3f", mrt));
                        System.out.println("Mean Retrieval Time: " + mrt);
                    }
                    break;
                case 4:
                    System.out.println("Thankyou for running this program.");
                    System.out.println("\n------------------------------------------------------");
                    System.exit(1);
                    break;
                default:
                    System.out.println("\nPlease make a valid choice.");
                    break;
            }
            System.out.println("\n------------------------------------------------------");
        }
    }
}