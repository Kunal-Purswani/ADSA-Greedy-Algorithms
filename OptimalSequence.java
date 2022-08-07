import java.util.*;

class OptimalSequence {
    public int getMax(int[] arr) {
        int max = 0;
        for (int i = 0; i < arr.length; i++)
            max = max < arr[i] ? arr[i] : max;
        return max;
    }

    public int getIndex(int max, int[] arr) {
        int index = -1;
        for (int i = 0; i < arr.length; i++)
            index = max == arr[i] ? i : index;
        return index;
    }

    public int getJobSeq(String[] seq, int[] profit, int[] deadline, int[] profitSorted, int[] deadlineSorted) {
        int totalProfit = 0, maxProfit, index, jobDeadline;
        for (int i = profit.length - 1; i >= 0; i--) {
            maxProfit = profitSorted[i];
            index = getIndex(maxProfit, profit);
            jobDeadline = deadline[index];
            for (int j = jobDeadline - 1; j >= 0; j--) {
                if (seq[j] == "") {
                    seq[j] = "" + (index + 1);
                    totalProfit += maxProfit;
                    break;
                } else
                    continue;
            }
        }
        return totalProfit;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        OptimalSequence os = new OptimalSequence();
        int totalProfit = 0, jobs, maxDeadline;
        int[] profit, deadline, profitSorted, deadlineSorted;
        String[] seq;
        do {
            System.out.print("Enter no. of jobs: ");
            jobs = Integer.parseInt(sc.nextLine());
            if (jobs < 1)
                System.out.println("No. of jobs should be greater than zero.");
        } while (jobs < 1);
        profit = new int[jobs];
        deadline = new int[jobs];
        profitSorted = new int[jobs];
        deadlineSorted = new int[jobs];
        for (int i = 0; i < jobs; i++) {
            System.out.print("Enter Profit on job " + (i + 1) + ": ");
            profit[i] = Integer.parseInt(sc.nextLine());
            profitSorted[i] = profit[i];
            System.out.print("Enter Deadline of job " + (i + 1) + ": ");
            deadline[i] = Integer.parseInt(sc.nextLine());
            deadlineSorted[i] = deadline[i];
        }
        Arrays.sort(profitSorted);
        Arrays.sort(deadlineSorted);
        maxDeadline = os.getMax(deadline);
        seq = new String[maxDeadline];
        for (int i = 0; i < maxDeadline; i++)
            seq[i] = "";
        totalProfit = os.getJobSeq(seq, profit, deadline, profitSorted, deadlineSorted);
        while (true) {
            System.out.println(
                    "\nProgrammed by Kunal Purswani Roll No. 59.\n\n----------------------- MENU -------------------------\n\tPress 1 for Job Sequence\n\tPress 2 for Total Profit\n\tPress 3 to get Most Profitable Job\n\tPress 4 to get Most Urgent Job\n\tPress 5 to Exit.\n------------------------------------------------------\n");
            System.out.print("Enter your choice: ");
            int ch = Integer.parseInt(sc.nextLine());
            switch (ch) {
                case 1:
                    String str = "Job Sequence: ";
                    for (int i = 0; i < seq.length; i++) {
                        if (!seq[i].equals(""))
                            str += "job" + seq[i] + ", ";
                    }
                    str = str.substring(0, str.length() - 2);
                    System.out.println(str);
                    break;
                case 2:
                    System.out.println("Total Profit on all jobs: "
                            + totalProfit);
                    break;
                case 3:
                    System.out.println(
                            "Most Profitable job is job" + (os.getIndex(profitSorted[profit.length - 1], profit) + 1)
                                    + " having profit " + profitSorted[profit.length - 1]);
                    break;
                case 4:
                    System.out.println("Most Urgent job is job" + (os.getIndex(deadlineSorted[0], deadline) + 1)
                            + " having deadline " + deadlineSorted[0]);
                    break;
                case 5:
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
