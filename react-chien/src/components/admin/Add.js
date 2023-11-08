import React, { useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";
function Add() {
    const data = {
        title: "",
        image: "",
        category: "",
        description: "",
        created_at: new Date()
    }
    const [successMessage, setSuccessMessage] = useState('');
    const [formdata,setformdata] = useState(data);
    const handleInput = (event) => {
        setformdata({...formdata, [event.target.name] : event.target.value})
    }
    const handleSubmit= (event)=> {
        event.preventDefault();
        axios.post('http://localhost:8080/blogs',formdata).then(
            response => {console.log(response);
            setSuccessMessage('Thêm thành công!')
    }).catch(err => console.log(err))
    }
    return (
        <div className="main">
            <div className="main--content">
                <h1>Add a new Blog</h1>
                <form onSubmit={handleSubmit}>
                    <div className="mb-3">
                        <label  className="form-label" >Title</label>
                        <input type="text" className="form-control" name="title" value={formdata.title}  onChange={handleInput}/>
                    </div>
                    <div className="mb-3">
                        <label  className="form-label">Image</label>
                        <input type="text" className="form-control" name="image" value={formdata.image} onChange={handleInput}/>
                    </div>
                    <div className="mb-3">
                        <label  className="form-label">Category</label>
                        <input type="text" className="form-control" name="category" value={formdata.category}  onChange={handleInput}/>
                    </div>
                    <div class="mb-3">
                        <label  className="form-label">Description</label>
                        <textarea class="mb-3 form-control" name="description" rows="10"
                            value={formdata.description} onChange={handleInput}  />
                    </div>
                    {successMessage && <div className="success-message">{successMessage}</div>}
                    <button type="submit"  className="btn btn-primary">Submit</button>
                    <Link to={"/admin"} className="btn btn-primary">Cancel</Link>
                </form>
            </div>
        </div>
    )
}
export default Add;