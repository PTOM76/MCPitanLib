package ml.pkom.mcpitanlib.api.util.math;

import net.minecraft.util.math.random.CheckedRandom;
import net.minecraft.util.math.random.RandomSeed;

import java.util.Random;

public class CRandom {

    private long seed = -1;

    private Random random;
    private CheckedRandom mcRandom;

    public static int BOTH_MODE = 0;
    public static int JAVA_RANDOM_MODE = 1;
    public static int MC_RANDOM_MODE = 2;
    public static int RECOMMEND_MODE = MC_RANDOM_MODE;

    // 0: Both, 1: Random of Java, 2: Random of Minecraft
    public CRandom(long seed, int mode) {
        setSeed(seed);
        if (getSeed() != -1) {
            switch (mode) {
                case 0:
                    setRandom(new Random(getSeed()));
                    setMcRandom(new CheckedRandom(getSeed()));
                case 1:
                    setRandom(new Random(getSeed()));
                case 2:
                    setMcRandom(new CheckedRandom(getSeed()));
            }
        }

        switch (mode) {
            case 0:
                setRandom(new Random());
                setMcRandom(new CheckedRandom(RandomSeed.getSeed()));
            case 1:
                setRandom(new Random());
            case 2:
                setMcRandom(new CheckedRandom(RandomSeed.getSeed()));
        }
    }

    public CRandom() {
        this(-1, RECOMMEND_MODE);
    }

    public CRandom(int mode) {
        this(-1, mode);
    }

    public CRandom(long seed) {
        this(seed, RECOMMEND_MODE);
    }

    public static CRandom create() {
        return new CRandom();
    }

    public static CRandom create(long seed) {
        return new CRandom(seed);
    }

    public void setSeed(long seed) {
        this.seed = seed;
    }

    public long getSeed() {
        return seed;
    }

    public CheckedRandom getMcRandom() {
        return mcRandom;
    }

    public Random getRandom() {
        return random;
    }

    public void setMcRandom(CheckedRandom mcRandom) {
        this.mcRandom = mcRandom;
    }

    public void setRandom(Random random) {
        this.random = random;
    }
}
