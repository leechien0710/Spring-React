import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import axios from "axios";
const Admin = (props) => {
    const [data, setData] = useState([]);
    useEffect(() => {
        const axiosConfig = {
            headers: {
                'X-API-Key': 'DogooChien'
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
            <div className="main--content" >
                <h1>Admin Home</h1>
                <Link to={'/add'} className="btn btn-primary">Add Blog</Link>
                <table className="table" >
                    <thead>
                        <tr>
                            <th scope="col">Title</th>
                            <th scope="col">Image</th>
                            <th scope="col">Category</th>
                            <th scope="col">Created At</th>
                            <th scope="col">Edit</th>
                            <th scope="col">Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        {data.map(blog => (
                            <tr key={blog.id}>
                                <td>{blog.title}</td>
                                <td><img src={blog.image} alt="Ảnh Blog" width={'100px'} height={'100px'} /></td>
                                <td>{blog.category}</td>
                                <td>{blog.created_at}</td>
                                <td><Link to={ `/edit/${blog.id}`} state ={{blog: blog}}>Edit</Link>
                                </td>
                                <td><Link to={`/delete/${blog.id}`} state ={{blog: blog}}>Delete</Link></td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </div>

    );
}
export default Admin;