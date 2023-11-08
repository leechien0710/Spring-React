import React from "react";
import { FaFacebookSquare, FaLinkedinIn } from "react-icons/fa";
import { AiFillInstagram } from "react-icons/ai"
import { BiLogoTwitter } from "react-icons/bi"
import { useState } from "react";
import { useEffect } from "react";
import axios from "axios";
import { Link } from "react-router-dom";
function Home() {
    const [data, setData] = useState([]);
    useEffect(() => {
        const axiosConfig = {
            headers: {
                'X-API-KEY': 'DogooChien' // Thay 'your-api-key-here' bằng giá trị API key của bạn
            }
        }
        axios.get('http://localhost:8080/blogs',axiosConfig)
            .then(response => {
                setData(response.data);
            })
            .catch(error => {
                console.error('Error fetching data:', error);
            });
    }, []);
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
                    {data.map(blog => (
                    <Link className="title--item__link" to={`/blog/${blog.id}`} state={{blog:blog}}>
                        <div className="blog-main">
                            <div className="blog--image">
                                <img src={blog.image} width={'300px'} height={'200px'} />
                            </div>
                            <div className="blog--content">
                                <h4>{blog.title}</h4>
                                <p className="author--text__blur">{blog.created_at} | {blog.category}</p>
                                <p className="blog--desc">{blog.description}</p>
                            </div>
                        </div>
                        </Link>
                    ))}
                </div>
            </div>
        </div>
    );
}
export default Home;