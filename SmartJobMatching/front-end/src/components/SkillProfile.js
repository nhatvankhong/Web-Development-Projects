import React from 'react';
import {MuiThemeProvider} from '@material-ui/core/styles';
import {createMuiTheme} from "@material-ui/core/styles/index";
import {Grid} from '@material-ui/core'
import IconButton from '@material-ui/core/IconButton';
import Tooltip from '@material-ui/core/Tooltip';
import DeleteButtonIcon from '@material-ui/icons/Delete'
import AddButtonIcon from '@material-ui/icons/Add'
import styled from "styled-components";
import {StyledDraggableItemContainer} from '../styles';
import Select from "./Select";
import {skillLevel} from './skill-level'
import {skillNames} from "./skill-names";
import {techNames} from './tech-names';
import {techLevel} from './tech-level';
import {yearOfExperience} from "./year-of-experience";
import Menu from "./Menu";
import Fab from "@material-ui/core/Fab";
import SaveIcon from '@material-ui/icons/Save';
import Box from "@material-ui/core/Box";
import Typography from "@material-ui/core/Typography";
import Link from "@material-ui/core/Link";
import Cookies from "js-cookie";
import {Redirect} from "react-router-dom";
import {BACKEND_ENDPOINT, FETCH_JOB_API_PATH, USER_PROFILE_CRU_API_PATH} from "../config";
import Alert from "./Alert";

const skillLevelOptions = skillLevel;
const skillNameOptions = skillNames;
const techLevelOptions = techLevel;
const techNameNameOptions = techNames;
const yearsOfExperienceOptions = yearOfExperience;

const theme = createMuiTheme({
    shape: {
        borderRadius: 0,
    }
});

const StyledActions = styled.div`
  position: absolute; 
  right: 40px;
`;

const StyledPaper = styled.div`
  box-shadow: 0 2px 4px -1px rgba(0,0,0,0.2), 0px 4px 5px 0px rgba(0,0,0,0.14), 0px 1px 10px 0px rgba(0,0,0,0.12);
  padding: 24px 16px;
  background: white;
  margin-top: 20px;
  margin-left: 100px;
  margin-right: 100px;
`;

const SectionTitle = styled.div`
    font-weight: bold;
    margin-left:80px;
    margin-top:30px;
`;

class SkillProfile extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            jobList: [],
            jobExpected: "",
            skills: [{name_level: "", name: "", level: ""}],
            experience: "",
            technologies: [{name_level: "", name: "", level: ""}],
            profileSaved: false
        }
    }

    handleAddSkill = () => {
        let newUserSkillList = this.state.skills;
        console.log("LATEST SKILL: ", newUserSkillList)
        newUserSkillList.push({name: "", level: ""});
        this.setState({
            ...this.state,
            skills: newUserSkillList
        })
    };

    handleAddTech = () => {
        let newUserTechList = this.state.technologies;
        newUserTechList.push({name: "", level: ""});
        this.setState({
            ...this.state,
            technologies: newUserTechList
        })
    };

    handleDeleteSkillRow(removedElem) {
        const {index, type} = removedElem;
        if (index != 0) {

            // [0] Cloning the Flow Component
            let clonedList;
            if (type == "skill") {
                clonedList = this.state.skills;
            } else {
                clonedList = this.state.technologies;
            }

            // [1] Removing it from array and update indexing
            clonedList.splice(index, 1);

            // [2] set the state
            if (type == "skill") {
                this.setState({
                    ...this.state,
                    skills: clonedList
                })
            } else {
                this.setState({
                    ...this.state,
                    technologies: clonedList
                })
            }
        }
    }

    handleYearOfExperienceSelected = event => {
        this.setState({
            ...this.state,
            experience: event.value
        })
    };

    handleSkillChange(item, event) {
        const {value} = event;
        const {type, index} = item;
        let newUserSkillList = this.state.skills;
        let skillRowIndx = newUserSkillList[index];
        if (type == "skill-name") {
            skillRowIndx.name = value;
        } else {
            skillRowIndx.level = value;
        }
        skillRowIndx.name_level = `${skillRowIndx.name}_${skillRowIndx.level}`;
        newUserSkillList[index] = skillRowIndx;
        this.setState({
            ...this.state,
            skills: newUserSkillList
        })
    };

    handleTechsChange(item, event) {
        const {value} = event;
        const {type, index} = item;
        let newUserTechList = this.state.technologies;
        let techRowIndx = newUserTechList[index];
        if (type == "tech-name") {
            techRowIndx.name = value;
        } else {
            techRowIndx.level = value;
        }
        techRowIndx.name_level = `${techRowIndx.name}_${techRowIndx.level}`;

        newUserTechList[index] = techRowIndx;
        this.setState({
            ...this.state,
            technologies: newUserTechList
        })
    };

    /**
     * METHOD TO CREATED/UPDATE USER PROFILE SKILL
     * */
    handleSaveSkillProfile = () => {
        const dataBody = JSON.stringify({
            jobExpected: this.state.jobExpected,
            experience: this.state.experience,
            credential: {
                userId: Cookies.get("cookies")
            },
            skills: this.state.skills,
            technologies: this.state.technologies
        });

        fetch(`${BACKEND_ENDPOINT}${USER_PROFILE_CRU_API_PATH}`, {
            method: 'post',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: dataBody
        })
            .then(response => response.json())
            .then(json => {
                console.log(json)
                this.setState({
                    ...this.state,
                    profileSaved: true
                })
            })
    };

    Copyright() {
        return (
            <Typography variant="body2" color="textSecondary" align="center" style={{marginBottom: "30px"}}>
                {'Copyright © '}
                <Link color="inherit" href="https://google.com/">
                    CS472 ::: PROF. KALU ::: TAURUS TEAM
                </Link>{' '}
                {new Date().getFullYear()}
                {'.'}
            </Typography>
        );
    }

    onJobSelected = event => {
        this.setState({
            ...this.state,
            jobExpected: event.value
        })
    };

    SelectJobComponent() {
        let jobPositions = [];
        this.state.jobList.map(job => jobPositions.push(job.title));
        jobPositions = jobPositions.map(position => {
            return {value: position, label: position}
        });
        return (
            <React.Fragment>
                <StyledPaper elevation={4}>
                    <div style={{"margin": "0px 20px 0px 20px"}}>
                        <Select
                            placeholder="Select Job Title to apply..."
                            options={jobPositions}
                            onChange={this.onJobSelected}
                            value={{label: this.state.jobExpected, value: this.state.jobExpected}}
                        />
                    </div>
                </StyledPaper>
            </React.Fragment>
        )
    }

    YearsOfExperienceComponent() {
        return (
            <StyledDraggableItemContainer styles={{border: '1px solid red'}}>
                <Select
                    placeholder="Experience range..."
                    options={yearsOfExperienceOptions}
                    onChange={this.handleYearOfExperienceSelected}
                    value={{label: this.state.experience, value: this.state.experience}}
                />
            </StyledDraggableItemContainer>
        )
    }

    SkillListComponent() {
        return (
            this.state.skills.map((skill, index) => {
                return (
                    <StyledDraggableItemContainer styles={{border: '1px solid red'}}>
                        <Grid container spacing={4} alignItems="center">
                            <Grid item xs={7}>
                                <Select
                                    placeholder="Select Skill"
                                    options={skillNameOptions}
                                    onChange={this.handleSkillChange.bind(this, {
                                        index: index,
                                        type: "skill-name"
                                    })}
                                    value={{value: skill.name, label: skill.name}}
                                />
                            </Grid>
                            <Grid item xs={3}>
                                <Select
                                    placeholder="Select Level"
                                    options={skillLevelOptions}
                                    onChange={this.handleSkillChange.bind(this, {
                                        index: index,
                                        type: "skill-level"
                                    })}
                                    value={{value: skill.level, label: skill.level}}
                                />
                            </Grid>
                            <StyledActions>
                                <div style={{"float": "left"}}>
                                    <Tooltip title='Add'>
                                        <IconButton onClick={this.handleAddSkill}>
                                            <AddButtonIcon fontSize="small"/>
                                        </IconButton>
                                    </Tooltip>
                                    <Tooltip title='Delete'>
                                        <IconButton style={{color: 'rgb(255, 32, 64)'}}
                                                    onClick={this.handleDeleteSkillRow.bind(this, {
                                                        index: index,
                                                        type: "skill"
                                                    })}>
                                            <DeleteButtonIcon fontSize="small"/>
                                        </IconButton>
                                    </Tooltip>
                                </div>
                            </StyledActions>
                        </Grid>
                    </StyledDraggableItemContainer>
                )
            })
        )
    }

    TechnologiesListComponent() {
        console.log("TECHNOLOGIES: ", this.state.technologies)
        return (
            this.state.technologies.map((tech, index) => {
                console.log(tech)
                return (
                    <StyledDraggableItemContainer styles={{border: '1px solid red'}}>
                        <Grid container spacing={4} alignItems="center">
                            <Grid item xs={7}>
                                <Select
                                    placeholder="Select Technology"
                                    options={techNameNameOptions}
                                    onChange={this.handleTechsChange.bind(this, {
                                        index: index,
                                        type: "tech-name"
                                    })}
                                    value={{value: tech.name, label: tech.name}}
                                />
                            </Grid>
                            <Grid item xs={3}>
                                <Select
                                    placeholder="Select Level"
                                    options={techLevelOptions}
                                    onChange={this.handleTechsChange.bind(this, {
                                        index: index,
                                        type: "tech-level"
                                    })}
                                    value={{value: tech.level, label: tech.level}}
                                />
                            </Grid>
                            <StyledActions>
                                <div style={{"float": "left"}}>
                                    <Tooltip title='Add'>
                                        <IconButton onClick={this.handleAddTech}>
                                            <AddButtonIcon fontSize="small"/>
                                        </IconButton>
                                    </Tooltip>
                                    <Tooltip title='Delete'>
                                        <IconButton style={{color: 'rgb(255, 32, 64)'}}
                                                    onClick={this.handleDeleteSkillRow.bind(this, {
                                                        index: index,
                                                        type: "tech"
                                                    })}>
                                            <DeleteButtonIcon fontSize="small"/>
                                        </IconButton>
                                    </Tooltip>
                                </div>
                            </StyledActions>
                        </Grid>
                    </StyledDraggableItemContainer>
                )
            })
        )
    }

    componentDidMount() {
        fetch(`${BACKEND_ENDPOINT}${USER_PROFILE_CRU_API_PATH}?userId=${Cookies.get("cookies")}`, {
            method: 'get',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
        })
            .then(response => response.json())
            .then(json => {
                console.log("USER PROFILE:\n", json)
                if (!json.message) {
                    this.setState({
                        ...this.state,
                        skills: json.skills,
                        technologies: json.technologies,
                        jobExpected: json.jobExpected,
                        experience: json.experience
                    })
                }
            });

        fetch(`${BACKEND_ENDPOINT}${FETCH_JOB_API_PATH}`, {
            method: 'post',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                userId: Cookies.get("cookies"),
            })
        })
            .then(response => response.json())
            .then(json => {
                this.setState({
                    ...this.state,
                    jobList: json.jobs
                })
            })
    }

    render() {
        console.log("INITIAL STATE:\n", this.state);
        if (!Cookies.get("cookies")) return <Redirect to="/login"/>;
        return (
            <MuiThemeProvider theme={theme}>
                <Alert
                    severity="success"
                    message="Item saved successfully!"
                    type="success"
                    open={this.state.profileSaved}
                    autoHideDuration={3000}
                >Profile Saved!</Alert>
                <Menu/>
                <Fab color="primary"
                     aria-label="add"
                     style={{
                         "backgroundColor": "#FF2040",
                         "margin": "30px 30px 30px 30px",
                         "position": "fixed",
                         "right": 0,
                         "top": 50
                     }}
                     onClick={this.handleSaveSkillProfile}
                >
                    <SaveIcon/>
                </Fab>
                <SectionTitle>
                    EXPECTED JOB POSITION
                </SectionTitle>
                {this.SelectJobComponent()}
                <SectionTitle>
                    YEARS OF EXPERIENCE
                </SectionTitle>
                {this.YearsOfExperienceComponent()}
                <SectionTitle>
                    YOUR SKILLS
                </SectionTitle>
                {this.SkillListComponent()}
                <SectionTitle>
                    TECHNOLOGIES ::: FRAMEWORKS ::: TOOLS
                </SectionTitle>
                {this.TechnologiesListComponent()}
                <Box mt={8}>
                    {this.Copyright()}
                </Box>
            </MuiThemeProvider>
        );
    }
}

export default SkillProfile;
