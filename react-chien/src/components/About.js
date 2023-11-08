import React from "react";
import { FaFacebookSquare, FaLinkedinIn } from "react-icons/fa";
import { AiFillInstagram } from "react-icons/ai"
import { BiLogoTwitter } from "react-icons/bi"
import { Link } from "react-router-dom";
function About() {
    
    return (
        <div className="main">
            <div className="main--content">
                <div className="author">
                    <div className="author--image">
                        <img src="https://th.bing.com/th/id/OIP.givKSmXznIN5fho1Gq6H6wHaKv?w=184&h=268&c=7&r=0&o=5&pid=1.7" alt="anh"
                            width={'100%'} height={'100%'} style={{ borderRadius: "50%" }} />
                    </div>
                    <p>Denail is a highly accomplished and versatile author, celebrated for their exceptional literary.</p>
                    <p className="author--text__blur">______________</p>
                    <p>Featured Posts</p>
                    <p className="author--text__blur">Featured Post is a spotlight article that showcases noteworthy content, often chosen for its relevance, quality, and significance.</p>
                    <p className="author--text__blur">______________</p>
                    <FaFacebookSquare className="author--icon" size={'25px'} />
                    <AiFillInstagram className="author--icon" size={'25px'} />
                    <BiLogoTwitter className="author--icon" size={'25px'} />
                    <FaLinkedinIn className="author--icon" size={'25px'} />
                    <p className="author--text__blur">BUILT WITH WEBFLOW</p>
                </div>
                <div className="blog">
                        <div className="blog-detail">
                            <div className="author-detail">
                                <h3>About me</h3>
                                <p >Denali is a prolific author known for their captivating storytelling and vivid imagination. Their works often explore complex human emotions and deliver powerful narratives that leave readers deeply moved. Denali's writing style is both evocative and thought-provoking, making them a celebrated figure in the literary world</p>
                                <h4>Something else here</h4>
                                <p>Denali, a masterful wordsmith, has penned numerous bestsellers across various genres, showcasing remarkable versatility. Their ability to craft compelling characters and immersive settings has earned them a dedicated fan base worldwide. Denali's literary contributions extend beyond novels, as they have also written thought-provoking essays and articles on a wide range of topics.

Readers often admire Denali's distinctive prose, which combines eloquence with a deep exploration of human nature. With a profound insight into the human condition, Denali's work delves into the complexities of relationships, identity, and the human psyche. Whether it's a heartwrenching drama or a thrilling adventure, Denali's storytelling prowess keeps readers eagerly turning the pages, eager to uncover the next literary gem. In the literary world, Denali's name is synonymous with exceptional storytelling and enduring impact.</p>
                                <Link to={"/contact"} class="btn btn-secondary" >Get In Touch</Link>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
    );
}
export default About;