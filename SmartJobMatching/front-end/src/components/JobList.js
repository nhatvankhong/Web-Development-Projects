import React, {useState, useEffect} from 'react';
import Box from '@material-ui/core/Box';
import Collapse from '@material-ui/core/Collapse';
import IconButton from '@material-ui/core/IconButton';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Typography from '@material-ui/core/Typography';
import Paper from '@material-ui/core/Paper';
import KeyboardArrowDownIcon from '@material-ui/icons/KeyboardArrowDown';
import KeyboardArrowUpIcon from '@material-ui/icons/KeyboardArrowUp';
import {withStyles} from '@material-ui/core/styles';
import Menu from "./Menu"
import {MuiThemeProvider} from '@material-ui/core/styles';
import {BACKEND_ENDPOINT, FETCH_JOB_API_PATH} from "../config";
import Alert from "./Alert";
import Slider from "@material-ui/core/Slider";
import Cookies from 'js-cookie'
import {Redirect} from "react-router-dom";


const tableHeaderTextStyled = {
    fontWeight: "bold"
};

const StyledTableCell = withStyles((theme) => ({
    head: {
        backgroundColor: theme.palette.primary.main,
        color: theme.palette.common.white,
    },
    body: {
        fontSize: 14,
    },
}))(TableCell);

const StyledTableRow = withStyles((theme) => ({
    root: {
        '&:nth-of-type(odd)': {
            backgroundColor: theme.palette.action.hover,
        },
    },
}))(TableRow);

function JobList() {
    const [load, setLoad] = useState(false);
    const [error, setError] = useState("");
    const [success, setSuccess] = useState(false);
    const [jobList, setJobList] = useState([]);
    const [filteredJobList, setFilteredJobList] = useState([]);
    const [open, setOpen] = useState(false);
    const [skillProfile, setSkillProfile] = useState(false);
    const [threshold, setThreshold] = useState(0);
    const [loadedData, setLoadedData] = useState(false);
    const [userId, setUserId] = useState(Cookies.get("cookies"));

    useEffect(() => {
        if (!loadedData) {
            setLoad(true);
            fetch(`${BACKEND_ENDPOINT}${FETCH_JOB_API_PATH}`, {
                method: 'post',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    userId: userId,
                })
            })
                .then(response => response.json())
                .then(json => {
                    setJobList(json.jobs);
                    setFilteredJobList(json.jobs);
                    setSkillProfile(json.hasProfile);
                    setUserId(Cookies.get("cookies"));
                    setLoadedData(true)
                    setLoad(false);
                    const {message, userId} = json;
                    if (userId === 0) {
                        setError(message)
                    } else {
                        setSuccess(true)
                    }
                });
        }
        // GET THE NEW JOB LIST WITH FILTERED PERCENTAGE
        let filteredArray = jobList;
        filteredArray = filteredArray.filter(job => job.matchedPercent>=threshold)
        console.log("FITER: ", filteredArray)
        setFilteredJobList(filteredArray)

    }, [threshold]);

    if(!Cookies.get("cookies")) return <Redirect to="/login"/>;

    return (
        <MuiThemeProvider>
            <Menu/>
            {skillProfile ? null : <Alert severity="warning" open={!skillProfile}>Missing Skill Profile!</Alert>}
            <div style={{
                "margin": "30px 50px 100px 30px",
                "position": "absolute",
                "right": 0,
                "top": 50,
                color:"#f50057"
            }}>
                <Typography id="discrete-slider" gutterBottom>
                    Filter Matching Percentage
                </Typography>
                <Slider
                    disabled={!skillProfile}
                    defaultValue={threshold}
                    getAriaValueText={setThreshold}
                    aria-labelledby="discrete-slider"
                    valueLabelDisplay="auto"
                    step={10}
                    marks
                    min={0}
                    max={100}
                />
            </div>
            <TableContainer component={Paper} style={{marginTop: "70px"}}>
                <Table aria-label="collapsible table">
                    <TableHead>
                        <TableRow>
                            <StyledTableCell/>
                            <StyledTableCell align="left" style={tableHeaderTextStyled}>Job Title</StyledTableCell>
                            <StyledTableCell align="center" style={tableHeaderTextStyled}>Location</StyledTableCell>
                            <StyledTableCell align="center" style={tableHeaderTextStyled}>Salary</StyledTableCell>
                            {skillProfile ? <StyledTableCell align="center" style={tableHeaderTextStyled}>Matching
                                Percentage</StyledTableCell> : null}
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {filteredJobList.map((job, index) => (
                            <React.Fragment>
                                <StyledTableRow>
                                    <StyledTableCell>
                                        <IconButton aria-label="expand row" size="small" onClick={() => setOpen(!open)}>
                                            {open ? <KeyboardArrowUpIcon/> : <KeyboardArrowDownIcon/>}
                                        </IconButton>
                                    </StyledTableCell>
                                    <StyledTableCell align="left">{job.title}</StyledTableCell>
                                    <StyledTableCell align="center">{job.location}</StyledTableCell>
                                    <StyledTableCell align="center">{job.salary}</StyledTableCell>
                                    {skillProfile ?
                                        <StyledTableCell align="center">{job.matchedPercent}</StyledTableCell> : null}
                                </StyledTableRow>
                                <StyledTableRow>
                                    <StyledTableCell style={{paddingBottom: 0, paddingTop: 0}} colSpan={6}>
                                        <Collapse in={open} timeout="auto" unmountOnExit>
                                            <Box margin={1}>
                                                <Table size="small" aria-label="purchases">
                                                    <TableBody>
                                                        <Typography style={{marginLeft: "80px"}}>
                                                            <h2> 💁‍♀️ JOB TITLE: {job.title}</h2>
                                                            <h2>DESCRIPTION:</h2>
                                                            <h5>{job.desc}</h5>
                                                            <h2>LOCATION: {job.location}</h2>
                                                            <h2>SALARY: ${job.salary}</h2>
                                                            <h2>REQUIRED SKILLS:</h2>
                                                            {job.skills.map(skill=>{
                                                                return(
                                                                    <ul>
                                                                        <li>{skill.name}, level: {skill.level}</li>
                                                                    </ul>
                                                                )
                                                            })}
                                                            <h2>REQUIRED TECHNOLOGIES:</h2>
                                                            {job.technologies.map(tech=>{
                                                                return(
                                                                    <ul>
                                                                        <li>{tech.name}, level: {tech.level}</li>
                                                                    </ul>
                                                                )
                                                            })}
                                                            {skillProfile ? <h2 style={{color: "red"}}>MISSED SKILLS:</h2> : null}
                                                            {job.missedSkills.map(skill=>{
                                                                return(
                                                                    <ul>
                                                                        <li>{skill.name}, level: {skill.level}</li>
                                                                    </ul>
                                                                )
                                                            })}
                                                        </Typography>
                                                    </TableBody>
                                                </Table>
                                            </Box>
                                        </Collapse>
                                    </StyledTableCell>
                                </StyledTableRow>
                            </React.Fragment>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </MuiThemeProvider>

    )
}

export default JobList;