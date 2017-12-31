package de.comeight.crystallogy.util.enums;

public enum EnumParticle {
    BASE_PARTICLE(0),
    CRYSTAL_PARTICLE(1),
    SQUARE_PARTICLE(2);

    private final byte META;

    EnumParticle(int meta) {
        this.META = (byte)meta;
    }

    public byte getMeta() {
        return META;
    }

    public static EnumParticle fromMeta(byte meta) {
        for(EnumParticle p : values()) {
            if(p.getMeta() == meta) {
                return p;
            }
        }
        return null;
    }
}
