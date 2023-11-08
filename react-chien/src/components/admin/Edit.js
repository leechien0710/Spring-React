import axios from "axios";
import React, { useState } from "react";
import { Link, useLocation, useParams } from "react-router-dom";
const Edit = (props) => {
    const { id } = useParams();
    const location = useLocation()
    const { blog } = location.state
    const data = {
        title: blog.title,
        image: blog.image,
        category: blog.category,
        description: blog.description,
    }
    const [successMessage, setSuccessMessage] = useState('');
    const [formdata, setformdata] = useState(data);
    const handleInput = (event) => {
        setformdata({ ...formdata, [event.target.name]: event.target.value })
    }
    const handleSubmit = (event) => {
        event.preventDefault();
        axios.put(`http://localhost:8080/blogs/${id}`, formdata).then(
            response => {
                console.log(response);
                setSuccessMessage('Sửa thành công!')
            }).catch(err => console.log(err))
    }
    return (
        <div className="main">
            <div className="main--content">
                <h1>Edit Blog</h1>
                <form onSubmit={handleSubmit}>
                    <div className="mb-3">
                        <label className="form-label" >Title</label>
                        <input type="text" className="form-control" name="title"
                            onChange={handleInput} value={formdata.title} />
                    </div>
                    <div className="mb-3">
                        <label className="form-label">Image</label>
                        <input type="text" className="form-control" name="image" value={formdata.image}
                            onChange={handleInput} placeholder={blog.image} />
                    </div>
                    <div className="mb-3">
                        <label className="form-label">Category</label>
                        <input type="text" className="form-control" name="category"
                            value={formdata.category} onChange={handleInput} placeholder={blog.category} />
                    </div>
                    <div class="mb-3">
                        <label className="form-label">Description</label>
                        <textarea class="mb-3 form-control" name="description" rows="10"
                            value={formdata.description} onChange={handleInput} placeholder={blog.description} />

                    </div>
                    {successMessage && <div className="success-message">{successMessage}</div>}
                    <button type="submit" className="btn btn-primary">Submit</button>
                    <Link to={"/admin"} className="btn btn-primary">Cancel</Link>
                </form>
            </div>
        </div>
    )
}
export default Edit