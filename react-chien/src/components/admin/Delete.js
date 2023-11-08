import axios from "axios";
import React from "react";
import { Link, useLocation, useNavigate, useParams } from "react-router-dom";
const Delete = (props) => {
    
    const  {id}  = useParams();
    const location = useLocation()
    const { blog } = location.state
    const navigate = useNavigate()
    const handleSubmit = (event) => {
        event.preventDefault();
        axios.delete(`http://localhost:8080/blogs/${id}`).then(
            response => {
                console.log(response);
                navigate('/admin');
            }).catch(err => console.log(err))
    }
    return (
        <div className="main">
            <div className="main--content">
                <h1>Delete Blog</h1>
                <form onSubmit={handleSubmit}>
                    <div className="mb-3">
                        <label className="form-label" >Title:</label>
                        <p>{blog.title} </p>
                    </div>
                    <div className="mb-3">
                        <label className="form-label">Image:</label><br></br>
                        <img src={blog.image} alt="Ảnh Blog" width={'100px'} height={'100px'} />
                    </div>
                    <div className="mb-3">
                        <label className="form-label">Category:</label>
                        <p>{blog.category} </p>
                    </div>
                    <div class="mb-3">
                        <label className="form-label">Description:</label>
                        <p>{blog.description} </p>
                    </div>
                    <button type="submit" className="btn btn-primary">Delete</button>
                    <Link to={"/admin"} className="btn btn-primary">Cancel</Link>
                </form>
            </div>
        </div>
    )
}
export default Delete