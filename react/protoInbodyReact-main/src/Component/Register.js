import React, { useState } from "react";

export default function Register() {
  const [userInfo, setUserInfo] = useState({
    userid: "",
    password: "",
    email: "",
    sex: "",
    location: "",
    birth: "",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setUserInfo({
      ...userInfo,
      [name]: value,
    });
  };

  const handleSubmit = async (event) => {
    event.preventDefault();

    try {
      const response = await fetch("http://localhost:8080/upload/register", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(userInfo),
      });

      if (response.ok) {
        console.log("User registered successfully");
        // 성공 시 추가적인 로직 (예: 리다이렉트)
      } else {
        console.error("Failed to register user");
        // 실패 시 추가적인 로직
      }
    } catch (error) {
      console.error("Error:", error);
    }
  };

  return (
    <div>
      <h2>Register</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>User ID:</label>
          <input
            type="text"
            name="userid"
            value={userInfo.userid}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Password:</label>
          <input
            type="password"
            name="password"
            value={userInfo.password}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Email:</label>
          <input
            type="email"
            name="email"
            value={userInfo.email}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Sex:</label>
          <div>
            <label>
              <input
                type="radio"
                name="sex"
                value="1"
                checked={userInfo.sex === "1"}
                onChange={handleChange}
                required
              />
              남자
            </label>
            <label>
              <input
                type="radio"
                name="sex"
                value="2"
                checked={userInfo.sex === "2"}
                onChange={handleChange}
                required
              />
              여자
            </label>
          </div>
        </div>
        <div>
          <label>Location:</label>
          <input
            type="text"
            name="location"
            value={userInfo.location}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Birth:</label>
          <input
            type="date"
            name="birth"
            value={userInfo.birth}
            onChange={handleChange}
            required
          />
        </div>
        <button type="submit">Register</button>
      </form>
    </div>
  );
}
