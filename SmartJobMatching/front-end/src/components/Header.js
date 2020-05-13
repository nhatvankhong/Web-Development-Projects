import React from "react";
import styled from "styled-components";
import Button from '@material-ui/core/Button';
import Link from "next/link"

const StyledHeaderWrapper = styled.div`
  padding-bottom: 55px;
`;

const StyledHeader = styled.header`
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 55px;
  border-bottom: 1px solid #ccc;
  position: fixed;
  width: 100%;
  background: #fff;
  z-index: 1000;
`;

const StyledLogo = styled.img`
  max-height: 80%;
  margin-left: 20px;
  cursor: pointer;
`;

const StyledNav = styled.nav`
  display: flex;
  align-items: center;
  margin-right: 30px;

  & > ul {
    display: flex;

    & > li {
      list-style-type: none;

      & > a {
        font-weight: 600;
        text-decoration: none;
        display: flex;
        align-items: center;
      }
    }
  }
`;

const StyledLi = styled.li`
  & > a {
    padding: 0 20px;
    height: 55px;
    color: #444444;
    letter-spacing: .05em;
    color: #444444;

    &:hover {
      color: #FF2040;
    }

    &:active {
      color: black;
    }
  }
`;

const StyledJoinLi = styled.li`
  display: flex;
  align-items: center;
  padding-left: 20px;
  cursor: pointer;

  & > a {
    color: white;
    background-color: #FF2040;
    padding: 7px 20px;
    border-radius: 20px;

    &:hover {
      background-color: rgb(255, 32, 64, 0.8);
    }
  }
`;

const Header = () => (
    <StyledHeaderWrapper>
        <StyledHeader>
            <Link href="/"><StyledLogo src="/Taurus-icon.png" /></Link>
            <StyledNav>
                <ul>
                    <StyledLi><a href="/login"><Button>LOGIN</Button></a></StyledLi>
                    <StyledJoinLi><a href="/sign-up"><Button>JOIN NOW</Button></a></StyledJoinLi>
                </ul>
            </StyledNav>
        </StyledHeader>
    </StyledHeaderWrapper>
);

export default Header;