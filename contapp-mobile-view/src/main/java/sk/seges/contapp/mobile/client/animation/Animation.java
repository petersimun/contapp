package sk.seges.contapp.mobile.client.animation;

public class Animation {
	private String name;
	private AnimationNames animationName;

	public enum AnimationNames {
		SLIDE, SLIDE_UP, DISSOLVE, FADE, FLIP, POP, SWAP;

	}

	@SuppressWarnings("unused")
	private Animation() {

	}

	public Animation(AnimationNames animationName, String displayName) {
		this.animationName = animationName;
		this.name = displayName;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AnimationNames getAnimationName() {
		return animationName;
	}

}
