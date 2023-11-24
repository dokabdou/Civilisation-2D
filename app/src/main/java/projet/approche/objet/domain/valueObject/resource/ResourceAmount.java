package projet.approche.objet.domain.valueObject.resource;

public class ResourceAmount implements ResourceItf {
	public final int amount;

	public ResourceAmount(int amount) {
		if (amount < 0) {
			throw new IllegalArgumentException("Resource amount cannot be negative");
		}
		this.amount = amount;
	}

	public ResourceAmount add(ResourceAmount resourceAmount) {
		return new ResourceAmount(this.amount + resourceAmount.amount);
	}

	public ResourceAmount sub(ResourceAmount resourceAmount) {
		return new ResourceAmount(this.amount - resourceAmount.amount);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ResourceAmount) {
			ResourceAmount resourceAmount = (ResourceAmount) obj;
			return this.amount == resourceAmount.amount;
		}
		return false;
	}

	public boolean isGreaterOrEqual(ResourceAmount resourceAmount) {
		return this.amount >= resourceAmount.amount;
	}

	public boolean isLessOrEqual(ResourceAmount resourceAmount) {
		return this.amount <= resourceAmount.amount;
	}

	public boolean isLess(ResourceAmount resourceAmount) {
		return this.amount < resourceAmount.amount;
	}

	public boolean isGreater(ResourceAmount resourceAmount) {
		return this.amount > resourceAmount.amount;
	}

	@Override
	public String toString() {
		return Integer.toString(amount);
	}

	public String toShortString() {
		if (amount < 1000) {
			return Integer.toString(amount);
		} else if (amount < 1000000) {
			return Integer.toString(amount / 1000) + "k";
		} else if (amount < 1000000000) {
			return Integer.toString(amount / 1000000) + "M";
		} else {
			return Integer.toString(amount / 1000000000) + "G";
		}
	}
}
