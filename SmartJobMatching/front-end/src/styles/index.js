import styled from 'styled-components';
import Paper from '@material-ui/core/Paper';
import {Grid} from '@material-ui/core';

export const StyledPaper = styled(Paper)`
  padding: 24px 16px;
`;

export const StyledDragHandlerWrapper = styled.div`
  position: absolute;
  left: 20px;
  top: 50%;
  transform: translateY(-50%);
`;

export const StyledDragHandler = styled.div`
    display: none;
    width: 9px;
    height: 22px;
    background-image: url('/assets/images/grip-gray.png');
    cursor: grab;
    margin: 0 auto;
`;

export const StyledDraggableItemContainer = styled.div`
  position: relative;
  border: 1px solid lightgrey;
  border-radius: 2px;
  padding: ${props => props.padding ? props.padding : '8px 8px 8px 40px'};
  margin-bottom: 20px;
  margin-left: 100px;
  margin-right: 100px;
  margin-top: 10px;
  box-shadow: 0 2px 4px -1px rgba(0,0,0,0.2), 0px 4px 5px 0px rgba(0,0,0,0.14), 0px 1px 10px 0px rgba(0,0,0,0.12);
  transition: background-color 0.2s ease;
  background-color: ${props =>
    props.isDragDisabled ? 'lightgrey' : props.isDragging ? '#f5fafe' : 'white'
  };
  &:hover {
    background-color: #f5fafe;
    .handle {
      display: block;
    }
  }
  &:last-child {
    margin-bottom: 0;
  }
  .handle {
    display: ${props => props.isDragging && 'block'}
  }
`;


export const StyledGridContainer = styled(Grid)`
  background: #FFF;
  margin-bottom: 20px;
  padding: 10px 20px;
  box-shadow: 0 2px 4px -1px rgba(0,0,0,0.2), 0px 4px 5px 0px rgba(0,0,0,0.14), 0px 1px 10px 0px rgba(0,0,0,0.12);
  position: relative;
  ::before {
      content: "";
      top: 87px;
      left: 14px;
      position: absolute;
      width: 0;
      height: 0;
      border-left: 10px solid transparent;
      border-right: 10px solid transparent;
      border-top: 10px solid #FFF;
      filter: drop-shadow(0px 4px 2px rgba(0,0,0,0.2));
  }
`;