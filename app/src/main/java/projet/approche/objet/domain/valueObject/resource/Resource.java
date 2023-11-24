package projet.approche.objet.domain.valueObject.resource;

public class Resource implements ResourceItf {

	public final ResourceType type;
	public final ResourceAmount amount;

	public Resource(ResourceType type, ResourceAmount amount) {
		this.type = type;
		this.amount = amount;
	}

	public Resource(ResourceType type, int amount) {
		this.type = type;
		this.amount = new ResourceAmount(amount);
	}

	public Resource add(Resource resource) {
		if (!this.type.equals(resource.type)) {
			throw new IllegalArgumentException("Cannot add resources of different types");
		}

		return new Resource(this.type, this.amount.add(resource.amount));
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Resource) {
			Resource resource = (Resource) obj;
			return this.type.equals(resource.type) && this.amount.equals(resource.amount);
		}
		return false;
	}

	@Override
	public String toString() {
		return type.toString() + " : " + amount.toString();
	}

	public String toShortString() {
		return type.toString() + " : " + amount.toShortString();
	}

}
