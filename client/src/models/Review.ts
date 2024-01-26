class Review {
    id: number;
    userEmail: string;
    date: string;
    rating: number;
    productId: number;
    reviewText?: string;

    constructor(
        id: number,
        userEmail: string,
        date: string,
        rating: number,
        productId: number,
        reviewText: string) {
        this.id = id;
        this.userEmail = userEmail;
        this.date = date;
        this.rating = rating;
        this.productId = productId;
        this.reviewText = reviewText;
    }
}

export default Review;