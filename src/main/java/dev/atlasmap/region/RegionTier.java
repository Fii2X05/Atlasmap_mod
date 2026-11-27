package dev.atlasmap.region;

import java.util.Locale;

/**
 * Tingkat wilayah administratif. Urutan ordinal = urutan hierarki dari yang paling luas.
 *
 * <p>Nilai {@link #thicknessMultiplier} dikali ketebalan dasar garis batas, dan
 * {@link #alphaMultiplier} dikali opacity region, sehingga batas tingkat atas tampil
 * lebih tebal dan lebih pekat di peta dibanding batas tingkat bawah.
 */
public enum RegionTier {
	NATION(3.0f, 1.0f),
	TOWN(1.0f, 0.85f),
	DISTRICT(1.0f, 0.6f),
	OUTPOST(1.0f, 0.45f);

	/** Tier yang dipakai untuk region lama yang belum punya field tier. */
	public static final RegionTier DEFAULT = TOWN;

	private final float thicknessMultiplier;
	private final float alphaMultiplier;

	RegionTier(float thicknessMultiplier, float alphaMultiplier) {
		this.thicknessMultiplier = thicknessMultiplier;
		this.alphaMultiplier = alphaMultiplier;
	}

	public float thicknessMultiplier() {
		return thicknessMultiplier;
	}

	public float alphaMultiplier() {
		return alphaMultiplier;
	}

	/** Parsing toleran untuk nilai dari regions.json; nilai tak dikenal jatuh ke {@link #DEFAULT}. */
	public static RegionTier fromId(String id) {
		if (id == null) {
			return DEFAULT;
		}
		try {
			return valueOf(id.trim().toUpperCase(Locale.ROOT));
		} catch (IllegalArgumentException e) {
			return DEFAULT;
		}
	}
}
