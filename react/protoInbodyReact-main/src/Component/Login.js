import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function Login() {
  const [userid, setUserId] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleSubmit = async (event) => {
    event.preventDefault();

    const userInfo = {
      userid,
      password,
    };

    try {
      const response = await fetch("http://172.30.113.136:8080/request/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(userInfo),
      });

      if (response.ok) {
        console.log("Login successful");
        sessionStorage.setItem("userid", userid); // 로그인한 사용자 정보 저장
        alert("로그인 성공!");
        navigate("/main"); //메인 페이지 이동
        // 성공 시 추가적인 로직 (예: 리다이렉트)
      } else {
        alert("로그인 실패! 아이디 또는 비밀번호를 확인하세요.");
        console.error("Invalid credentials");
        // 실패 시 추가적인 로직
      }
    } catch (error) {
      alert("서버 오류 발생! 관리자에게 문의하세요.");
      console.error("Error:", error);
    }
  };

  return (
    <div>
      <h2>Login</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>User ID:</label>
          <input
            type="text"
            value={userid}
            onChange={(e) => setUserId(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Password:</label>
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        <button type="submit">Login</button>
      </form>
    </div>
  );
}
