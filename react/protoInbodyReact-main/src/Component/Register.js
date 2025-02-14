import React, { useState } from "react";
import { useNavigate } from "react-router-dom"; // 페이지 이동을 위한 훅

export default function Register() {
  const navigate = useNavigate(); // 페이지이동 userNavigate()

  const [userInfo, setUserInfo] = useState({
    userid: "",
    password: "",
    email: "",
    sex: "",
    location: "",
    birth: "",
  });

  // 현재 날짜 기준으로 최소 1년 전 날짜 계산 (오늘 날짜나 미래 날짜 선택시 데이터베이스에 부적절한 값이 적용됨됨)
  const today = new Date().toISOString().split("T")[0]; // YYYY-MM-DD 형식
  const minBirthDate = new Date();
  minBirthDate.setFullYear(minBirthDate.getFullYear() - 100); // 최대 100년 전까지 입력 가능
  const maxBirthDate = new Date();
  maxBirthDate.setFullYear(maxBirthDate.getFullYear() - 1); // 최소 1살부터 가입 가능

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
        alert("회원가입이 성공적으로 완료되었습니다.");
        console.log("User registered successfully");
        navigate("/login");// 가입 성공시 login페이지 이동동
      } else {
        alert("회원가입 실패! 입력한 정보를 다시 확인해주세요.");
        console.error("Failed to register user");
        // 실패 시 추가적인 로직
      }
    } catch (error) {
      alert("관리자에게 문의해주세요.")
      console.error("Error:", error);
    }
  };

  return (
    <div>
      <h2>회원가입</h2>
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
            min={minBirthDate.toISOString().split("T")[0]} // 최대 100년 전까지 입력 가능
            max={maxBirthDate.toISOString().split("T")[0]} // 최소 1살부터 가입 가능
            required
          />
        </div>
        <button type="submit">회원가입</button>
      </form>
    </div>
  );
}
